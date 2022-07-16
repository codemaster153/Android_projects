package com.lkb.baseandroidproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContent {
            BaseAndroidProjectTheme {
                val viewModel = viewModel<PostViewModel>()
                val posts = viewModel.posts.collectAsState(initial = emptyList())
//                val countDownTimer = viewModel.countDownFlow.collectAsState(
//                    initial = 10
//                )
//                viewModel.error.observe(this) { data ->
//                    Toast.makeText(this.applicationContext, data, Toast.LENGTH_SHORT).show()
//                }
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
                            items(posts.value) { post ->
                               Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                                   Text(text = post.body)
                               }
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