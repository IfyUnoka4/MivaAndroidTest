package com.example.mivaandroidtest.navigation

enum class Screens(
    private val args: List<String>? = null
) {
    Home,
    Classes,
    Subscribe,
    Downloads,
    More,
    SubjectDetails(
        args = listOf(
            BundleKeys.SubjectDetails.subjectId,
        ),
    ),
    ChapterDetails(
        args = listOf(
            BundleKeys.ChapterDetails.chapterLessons,
        ),
    ),
    MediaPlayer(
        args = listOf(
            BundleKeys.MediaPlayer.videoUrl,
        ),
    );


    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }

    fun withArgs(vararg args: Any): String {
        val destination = StringBuilder()
        args.forEach { arg -> destination.append("/$arg") }
        return name + destination
    }
}

object BundleKeys {
    object SubjectDetails {
        const val subjectId = "subjectId"
    }
    object ChapterDetails {
        const val chapterLessons = "chapterLessons"
    }
    object MediaPlayer {
        const val videoUrl = "videoUrl"
    }
}