package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val service = PostsService.create()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAndroidProjectTheme {
                val refreshScope = rememberCoroutineScope()
                var refreshing by remember { mutableStateOf(false) }
                var itemCount by remember { mutableIntStateOf(15) }

                fun refresh() = refreshScope.launch {
                    refreshing = true
                    delay(1500)
                    itemCount += 5
                    refreshing = false
                }

                val state = rememberPullRefreshState(refreshing, ::refresh)

                Box(Modifier.pullRefresh(state)) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        if (!refreshing) {
                            items(itemCount) {
                                ListItem { Text(text = "Item ${itemCount - it}") }
                            }
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }
}