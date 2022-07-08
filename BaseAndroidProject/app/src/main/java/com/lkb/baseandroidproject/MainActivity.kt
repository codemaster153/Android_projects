package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAndroidProjectTheme {
                val viewModel = viewModel<MainViewModel>()
                val countDownTimer = viewModel.countDownFlow.collectAsState(
                    initial = 10
                )
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = countDownTimer.value.toString(), modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseAndroidProjectTheme {
        Greeting("Android")
    }
}