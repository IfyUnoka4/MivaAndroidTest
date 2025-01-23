package com.example.mivaandroidtest.presentation.screens.player

import android.content.res.Configuration
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.data.datasources.remote.model.Lesson
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.Orange
import kotlinx.coroutines.delay


@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerScreen(navController: NavHostController, lesson: Lesson?) {
    val viewModel: PlayerViewModel = hiltViewModel()
    val currentVideo by viewModel.currentVideo.collectAsState()
    val context = LocalContext.current
    val currentTime = remember { mutableLongStateOf(0L) }
    val bookmarks by viewModel.bookmarks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var noteText by remember { mutableStateOf("") }


    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(viewModel.videoCacheManager.getCacheDataSourceFactory()))
            .build()
    }

    LaunchedEffect(lesson) {
        lesson?.videoUrl?.let { url ->
            viewModel.setVideoList(listOf(url))
        }
    }

    LaunchedEffect(currentVideo) {
        currentVideo?.let { url ->
            val mediaItem = MediaItem.fromUri(url)
            val cacheDataSourceFactory = viewModel.videoCacheManager.getCacheDataSourceFactory()
            val mediaSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(mediaItem)

            exoPlayer.setMediaSource(mediaSource)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }


    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime.longValue = exoPlayer.currentPosition
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        VideoPlayerCard(navController, exoPlayer)
        Spacer(modifier = Modifier.height(Dimens.space24))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.space12)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    showDialog = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = stringResource(R.string.bookmark),
                        tint = Orange
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = stringResource(R.string.share),
                        tint = Orange
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimens.space16))
            Text(
                text = lesson?.title.orEmpty(),
                fontSize = FontSize.fontSize18,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(Dimens.space30))

            if (bookmarks.isNotEmpty())
                Text(
                    text = stringResource(id = R.string.bookmarks),
                    fontSize = FontSize.fontSize14,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            Spacer(modifier = Modifier.height(Dimens.space8))

            LazyColumn {
                items(bookmarks) { bookmark ->
                    Text(
                        text = "${bookmark.note} - Go to ${bookmark.timestamp / 1000}s",
                        modifier = Modifier.clickable {
                            exoPlayer.seekTo(bookmark.timestamp)
                        }
                    )
                    Spacer(modifier = Modifier.height(Dimens.space8))
                }
            }
        }

        if (showDialog) {
            val timestamp = exoPlayer.currentPosition
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(R.string.add_bookmark)) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            label = { Text(stringResource(R.string.enter_note)) }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.addBookmark(timestamp, noteText)
                        showDialog = false
                        noteText = ""
                    }) {
                        Text(stringResource(R.string.save))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }

    }

}

