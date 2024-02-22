package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    val viewModel:MainViewModel by viewModel()
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BaseAndroidProjectTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var isVisible by remember {
                        mutableStateOf(false)
                    }

                    val context = LocalContext.current

                    var visibleTime by remember {
                        mutableLongStateOf(0L)
                    }

                    LaunchedEffect(isVisible) {

                        if (isVisible) {
                            Toast.makeText(context, "😆 item visible $isVisible", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "🥵 Item visible $isVisible", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    Box(modifier = Modifier.fillMaxSize()){
                        LazyColumn {
                            item {
                                Box (modifier = Modifier.padding(20.dp).isVisible(threshold = 100) {
                                   isVisible = it
                               }
                                ){

                                    Text(text = "Hello There I am top", Modifier.padding(20.dp))
                                }
                            }
                            items(20){ item->
//                               val modifier =  if(item==0) Modifier.isVisible(threshold = 100) {
//                                   isVisible = it
//                               } else Modifier
                               Box(modifier = Modifier
                                   .border(1.dp, color = Color.Gray)
                                   .fillMaxWidth()){
                                   Column() {
                                       Text(text = "Heading Title $item")
                                       Spacer(modifier = Modifier.padding(20.dp))
                                       Text(text = "body text here it is --- $item")
                                   }
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
        Greeting("Android")
    }
}

@Composable
fun Modifier.isVisible(
    threshold: Int,
    onVisibilityChange: (Boolean) -> Unit
): Modifier {

    return this then Modifier.onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
        val layoutHeight = layoutCoordinates.size.height
        val thresholdHeight = layoutHeight * threshold / 100
        val layoutTop = layoutCoordinates.positionInRoot().y
        val layoutBottom = layoutTop + layoutHeight



        // This should be parentLayoutCoordinates not parentCoordinates
        val parent =
            layoutCoordinates.parentLayoutCoordinates

        parent?.boundsInRoot()?.let { rect: Rect ->
            val parentTop = rect.top
            val parentBottom = rect.bottom
            Log.d(">>>","layouttop = $layoutTop and layoutBottom= $layoutBottom")
            Log.d(">>>","parentTop = $parentTop and parentBottom= $parentBottom")
            if (
                layoutBottom<=214
            ) {
                onVisibilityChange(true)
                Log.d(">>> true","")
            } else if(layoutBottom>0)  {
                onVisibilityChange(false)
                Log.d(">>> false","")

            }
        }
    }
}