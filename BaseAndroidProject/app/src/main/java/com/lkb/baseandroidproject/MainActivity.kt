package com.lkb.baseandroidproject

import Post
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
                    Scaffold(topBar = {
                        TopAppBar {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Hello",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }) {
                        LazyColumn {
                            items(5) {
                                Text(text = "hello")

                            }
                        }
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
        TopAppBar() { Text("hello") }
    }
}