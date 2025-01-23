package com.example.mivaandroidtest.presentation.screens.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer
import com.example.mivaandroidtest.ui.theme.Dimens


@Composable
fun PlaybackControls(exoPlayer: ExoPlayer) {
    val isPlaying = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.space16),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { exoPlayer.seekBack() }) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Rewind 10s",
                tint = Color.White
            )
        }

        IconButton(onClick = {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
            isPlaying.value = exoPlayer.isPlaying
        }) {
            Icon(
                imageVector = if (isPlaying.value) Icons.Default.PlayArrow else Icons.Default.PlayArrow,
                contentDescription = "Play/Pause",
                tint = Color.White
            )
        }

        IconButton(onClick = { exoPlayer.seekForward() }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Forward 10s",
                tint = Color.White
            )
        }
    }
}
