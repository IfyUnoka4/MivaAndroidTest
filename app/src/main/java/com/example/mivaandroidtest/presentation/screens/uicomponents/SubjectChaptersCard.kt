package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.Orange
import com.example.mivaandroidtest.ui.theme.TextBlack
import com.example.mivaandroidtest.ui.theme.TextBlack50

@Composable
fun SubjectChaptersCard(modifier: Modifier = Modifier, chapter: MivaChapter) {
    Card(modifier = modifier
        .fillMaxWidth()
        .background(Color.White, RoundedCornerShape(Dimens.space12)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(Dimens.space12)) {
            Image(
                id = R.drawable.maths,
                contentDescription = stringResource(R.string.subject_icon),
            )
            Spacer(modifier = modifier.width(Dimens.space25))
            Column {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Chapter 1",
                        fontSize = FontSize.fontSize12,
                        fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
                        color = TextBlack50,
                        modifier = modifier.alpha(0.5f)
                    )
                    Spacer(modifier = modifier.width(Dimens.space12))
                    LessonsNumberCard(chapter.lessons.size)
                }
                Spacer(modifier = modifier.height(Dimens.space8))
                Text(
                    text = chapter.title,
                    fontSize = FontSize.fontSize16,
                    fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
                    color = TextBlack
                )
                Spacer(modifier = modifier.height(Dimens.space12))
                LinearProgressIndicator(
                    progress = 0.45f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.space6)
                        .clip(RoundedCornerShape(Dimens.space3)),
                    color = Orange,
                    trackColor = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSubjectChaptersCard() {
    SubjectChaptersCard(chapter = MivaChapter(""))
}