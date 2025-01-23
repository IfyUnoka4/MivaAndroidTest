package com.example.mivaandroidtest.presentation.screens.home.chapter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.navigation.Screens
import com.example.mivaandroidtest.presentation.screens.uicomponents.LessonVideoCard
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.TextBlack
import com.example.mivaandroidtest.utils.encodeToJson
import com.example.mivaandroidtest.utils.encodeToUrl

@Composable
fun ChapterDetailsScreen(
    navController: NavHostController,
    chapter: MivaChapter?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.space16, vertical = Dimens.space24),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(R.string.back),
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(Dimens.space40))
        Text(
            text = stringResource(R.string.chapter_1),
            fontSize = FontSize.fontSize12,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack,
            modifier = Modifier.alpha(0.5f)
        )
        Spacer(modifier = Modifier.height(Dimens.space8))
        Text(
            text = chapter?.title.orEmpty(),
            fontSize = FontSize.fontSize24,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack
        )
        Spacer(modifier = Modifier.height(Dimens.space8))
        Text(
            text = "${chapter?.lessons?.size} Lessons",
            fontSize = FontSize.fontSize12,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack,
            modifier = Modifier.alpha(0.5f)
        )

        Spacer(modifier = Modifier.height(Dimens.space40))

        LazyColumn {
            items(chapter?.lessons.orEmpty()) {lesson ->
                val videoUrl = lesson.encodeToJson().encodeToUrl()

                LessonVideoCard(title = lesson.title, modifier = Modifier.clickable {
                    navController.navigate(Screens.MediaPlayer.withArgs(videoUrl))
                })
            }
        }
    }
}