package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.Orange


@Composable
fun LessonVideoCard(
    modifier: Modifier = Modifier,
    title: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.space8),
        shape = RoundedCornerShape(Dimens.space8),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(Dimens.space4)
    ) {
        Row(
            modifier = modifier.padding(Dimens.space12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .size(Dimens.space50)
                    .clip(RoundedCornerShape(Dimens.space8))
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = stringResource(R.string.play_video),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(Dimens.space32)
                        .background(Color.White, CircleShape)
                        .padding(Dimens.space4),
                    tint = Color.Red
                )

                LinearProgressIndicator(
                    progress = 0.4f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.space4)
                        .align(Alignment.BottomStart),
                    color = Orange,
                    trackColor = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.width(Dimens.space12))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = FontSize.fontSize14,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(Dimens.space4))

                Text(
                    text = "8mins",
                    fontSize = FontSize.fontSize12,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(Dimens.space8))

            Icon(
                painter = painterResource(id = R.drawable.ic_download,),
                contentDescription = stringResource(R.string.download),
                tint = Orange
            )
        }
    }
}
