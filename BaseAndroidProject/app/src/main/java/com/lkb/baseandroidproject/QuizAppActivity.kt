package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lkb.baseandroidproject.ui.theme.*


@ExperimentalMaterial3Api
class QuizAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            BaseAndroidProjectTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
                Scaffold { values ->
                    Box(
                        modifier = Modifier
                            .padding(values)
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        GradientStartColor,
                                        GradientEndColor
                                    )
                                )
                            )
                    ) {
                        QuizApp(values = values)
                    }
                }

            }
        }
    }
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    values: PaddingValues,
    viewModel: QuizViewModel = viewModel(),
) {
    NavHost(navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController, values, viewModel)
        }
        composable(route = "quiz") {
            QuizPage(navController, values, viewModel)
        }
        composable(route = "result") {
            ResultPage(navController, values, viewModel)
        }
    }
}

@Composable
fun ResultPage(navController: NavHostController, values: PaddingValues, viewModel: QuizViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {
        TwinTextWidget(
            first = "Lest see, How you did?",
            second = "Your score is 10 of 10."
        )
    }
}

@Composable
fun HomeScreen(navController: NavHostController, values: PaddingValues, viewModel: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TwinTextWidget(
            first = viewModel.text,
            second = "Start playing the daily quiz game."
        )
        Spacer(modifier = Modifier.weight(0.5f))
        QuizButton(text = "Start", onClick = {
            viewModel.reset()
            navController.navigate("quiz")
        })
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun QuizPage(navController: NavHostController, values: PaddingValues, viewModel: QuizViewModel) {
    val question = viewModel.uiState.collectAsState()
    val navigateHome = { navController.popBackStack() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        when (question.value) {
            is QuizState.Initial -> {
                Spacer(modifier = Modifier.weight(1f))
                (question.value as QuizState.Initial).data?.question?.let {
                    TwinTextWidget(
                        first = "question ${viewModel.currentIndex + 1} of 10",
                        second = it
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))
                (question.value as QuizState.Initial<QuizListModel>).data?.options?.forEach {
                    QuizButton(text = it.value, onClick = { viewModel.evaluate(it) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            is QuizState.Correct -> {
                Spacer(modifier = Modifier.weight(1f))
                val quiz = (question.value as QuizState.Correct).data
                quiz?.question?.let {
                    TwinTextWidget(
                        first = "question ${viewModel.currentIndex + 1} of 10",
                        second = it
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))
                quiz?.options?.forEach {
                    if (it.tag == quiz.selectedTag) {
                        QuizButton(text = it.value,
                            onClick = { },
                            imageRes = R.drawable.ic_tick_mark,
                            bgColor = GreenButtonColor,
                            txtColor = Color.White)
                    } else {
                        QuizButton(text = it.value)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            is QuizState.Incorrect -> {
                Spacer(modifier = Modifier.weight(1f))
                val quiz = (question.value as QuizState.Incorrect).data
                quiz?.question?.let {
                    TwinTextWidget(
                        first = "question ${viewModel.currentIndex + 1} of 10",
                        second = it
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))
                quiz?.options?.forEach {
                    if (it.tag == quiz.selectedTag) {
                        QuizButton(text = it.value,
                            onClick = { },
                            imageRes = R.drawable.ic_cross,
                            bgColor = ErrorColor,
                            txtColor = Color.White)
                    } else {
                        QuizButton(text = it.value)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            is QuizState.End -> {
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    TwinTextWidget(
                        first = "here is your result",
                        second = "Your score is ${viewModel.totalScore} out of 10"
                    )
                    Spacer(modifier = Modifier.weight(0.25f))
                    QuizButton(text = "Play Again", onClick = {
                        navigateHome.invoke()
                    })
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            else -> {}
        }.exhaustive
        Spacer(modifier = Modifier.weight(1f))
    }
}


