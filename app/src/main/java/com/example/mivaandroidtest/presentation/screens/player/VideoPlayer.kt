package com.example.mivaandroidtest.presentation.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.Dimensions.VideoPlayerHeight

@Composable
fun VideoPlayer(navController: NavController, exoPlayer: ExoPlayer, modifier: Modifier = Modifier) {
    var isFullScreen by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    Box(modifier = if (isFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = modifier
        )
    }

    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .padding(Dimens.space16)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.toggle_fullscreen),
            tint = Color.White
        )
    }
}

@Composable
fun VideoPlayerCard(navController: NavHostController, exoPlayer: ExoPlayer) {
    Card(
        shape = RoundedCornerShape(Dimens.space12),
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.space16),

        ) {
        Column {
            Box(modifier = Modifier.height(VideoPlayerHeight)) {

                VideoPlayer(navController, exoPlayer, modifier = Modifier.fillMaxSize())
            }
        }
    }
}