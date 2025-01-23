package com.example.mivaandroidtest.presentation.screens.home.chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mivaandroidtest.data.datasources.cache.ChapterViewState
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.domain.usecase.GetChapterUseCase
import com.example.mivaandroidtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChaptersViewModel @Inject constructor(
   private val chapterUseCase: GetChapterUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(ChapterViewState())
    val uiState: StateFlow<ChapterViewState> get() = _uiState

    init {
        getChapters()
    }

    private fun getChapters() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            chapterUseCase.getChapters().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, chapters = Chapter.toMivaChapter(response.data.orEmpty())) }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = response.error
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

}