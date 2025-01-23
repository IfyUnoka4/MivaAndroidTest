package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.Orange

@Composable
fun ResumeLearningCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .background(Orange, RoundedCornerShape(Dimens.space12))
        .padding(Dimens.space12)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                id = R.drawable.ic_resume_play,
                contentDescription = stringResource(R.string.resume),
            )
            Spacer(modifier = modifier.width(Dimens.space25))
            Column {
                Text(
                    text = "Introduction to \nBiology",
                    fontSize = FontSize.fontSize16,
                    fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
                    color = Color.White
                )
                Spacer(modifier = modifier.height(Dimens.space4))
                Text(
                    text = stringResource(R.string.you_ve_watched_0_of_5_lessons),
                    fontSize = FontSize.fontSize10,
                    fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewResumeLearningCard() {
    ResumeLearningCard()
}