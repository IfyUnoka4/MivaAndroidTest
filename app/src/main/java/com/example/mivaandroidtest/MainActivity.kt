package com.example.mivaandroidtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mivaandroidtest.navigation.BundleKeys
import com.example.mivaandroidtest.navigation.Screens
import com.example.mivaandroidtest.presentation.screens.home.chapter.ChapterDetailsScreen
import com.example.mivaandroidtest.presentation.screens.home.HomeScreen
import com.example.mivaandroidtest.presentation.screens.home.SubjectDetailsScreen
import com.example.mivaandroidtest.presentation.screens.player.VideoPlayerScreen
import com.example.mivaandroidtest.presentation.screens.uicomponents.MyBottomAppBar
import com.example.mivaandroidtest.ui.theme.MivaAndroidTestTheme
import com.example.mivaandroidtest.utils.decodeFromUrl
import com.example.mivaandroidtest.utils.decodeJsonString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            MivaAndroidTestTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { if (currentRoute == Screens.Home.name) {
                            MyBottomAppBar()
                        } }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screens.Home(),
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(Screens.Home()) {
                                HomeScreen(
                                    navController = navController
                                )
                            }
                            composable(Screens.SubjectDetails(),
                                arguments = listOf(navArgument(BundleKeys.SubjectDetails.subjectId) {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                val subjectId =
                                    backStackEntry.arguments?.getString(BundleKeys.SubjectDetails.subjectId)
                                SubjectDetailsScreen(navController, subjectId)
                            }
                            composable(Screens.ChapterDetails(),
                                arguments = listOf(navArgument(BundleKeys.ChapterDetails.chapterLessons) {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                val chapterLessons =
                                    backStackEntry.arguments?.getString(BundleKeys.ChapterDetails.chapterLessons)
                                ChapterDetailsScreen(navController, chapterLessons?.decodeFromUrl()?.decodeJsonString())
                            }
                            composable(Screens.MediaPlayer(),
                                arguments = listOf(navArgument(BundleKeys.MediaPlayer.videoUrl) {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                val lesson =
                                    backStackEntry.arguments?.getString(BundleKeys.MediaPlayer.videoUrl)
                                VideoPlayerScreen(navController, lesson?.decodeFromUrl()?.decodeJsonString())
                            }
//                            composable(Screens.SUBSCRIBE()) { SubscribeScreen() }
//                            composable(Screens.DOWNLOADS()) { DownloadsScreen() }
//                            composable(Screens.MORE()) { MoreScreen() }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MivaAndroidTestTheme {
        Greeting("Android")
    }
}