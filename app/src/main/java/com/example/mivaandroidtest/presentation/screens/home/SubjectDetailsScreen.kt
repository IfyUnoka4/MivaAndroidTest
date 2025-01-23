package com.example.mivaandroidtest.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.mivaandroidtest.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.navigation.Screens
import com.example.mivaandroidtest.presentation.screens.home.chapter.ChaptersViewModel
import com.example.mivaandroidtest.presentation.screens.uicomponents.ResumeLearningCard
import com.example.mivaandroidtest.presentation.screens.uicomponents.SearchView
import com.example.mivaandroidtest.presentation.screens.uicomponents.SubjectChaptersCard
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.TextBlack
import com.example.mivaandroidtest.utils.encodeToJson
import com.example.mivaandroidtest.utils.encodeToUrl


@Composable
fun SubjectDetailsScreen(navController: NavHostController, subjectId: String?) {
    val searchTextState = rememberSaveable { mutableStateOf("") }
    val chaptersViewModel: ChaptersViewModel = hiltViewModel()
    val chapterViewState by chaptersViewModel.uiState.collectAsState()

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
            text = subjectId.orEmpty(),
            fontSize = FontSize.fontSize24,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack
        )
        val chapterList: List<MivaChapter>? = chapterViewState.chapters
        Text(
            text = "${chapterList?.size} Chapter / ${chapterList?.sumOf { it.lessons.size }} Lessons",
            fontSize = FontSize.fontSize12,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack,
            modifier = Modifier.alpha(0.5f)
        )
        Spacer(modifier = Modifier.height(Dimens.space26))

        SearchView(searchTextState, hintText = stringResource(R.string.search_for_a_lesson))

        Spacer(modifier = Modifier.height(Dimens.space24))

        Text(
            text = "Resume learning",
            fontSize = FontSize.fontSize24,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack
        )
        Spacer(modifier = Modifier.height(Dimens.space12))

        ResumeLearningCard()

        Spacer(modifier = Modifier.height(Dimens.space24))

        Text(
            text = stringResource(R.string.chapters),
            fontSize = FontSize.fontSize24,
            fontFamily = FontFamily(Font(R.font.mulish_bold, FontWeight.Bold)),
            color = TextBlack
        )
        Spacer(modifier = Modifier.height(Dimens.space12))
        if (chapterViewState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = TextBlack
                )
            }
        }
        LazyColumn {
            items(chapterList.orEmpty()) { chapter ->
                val mivaChapterDataJson = chapter.encodeToJson().encodeToUrl()
                SubjectChaptersCard(modifier = Modifier.clickable {
                    navController.navigate(Screens.ChapterDetails.withArgs(mivaChapterDataJson))
                }, chapter = chapter)
            }
        }
    }
}