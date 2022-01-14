package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAndroidProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.padding(all = 20.dp)) {
            Text(text = "First item")
        }
        Box(
            modifier = Modifier
                .padding(all = 10.dp)
                .weight(1f)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 25.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Add 5 items
                items(20) { index ->
                    if (index == 5) {
                        Row() {
                            LazyRow() {
                                items(5) { index1 ->
                                    Box(modifier = Modifier.padding(all = 20.dp)) { Text(text = "Hello$index1") }

                                }
                            }
                        }
                    }
                    //not allowed
//                    if(index==12){
//                        Column() {
//                            LazyColumn(){
//                                items(5){
//                                    Box(modifier = Modifier.background(color = Color.Blue)){}
//                                    Text("Hey")
//                                }
//                            }
//                        }
//                    }
                    Box(modifier = Modifier.padding(all = 20.dp)) { Text(text = "Item: $index") }

                }
            }
        }

        Box(modifier = Modifier.padding(all = 20.dp)) {
            Text(text = "Last item")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseAndroidProjectTheme {
        Greeting("Android")
    }
}