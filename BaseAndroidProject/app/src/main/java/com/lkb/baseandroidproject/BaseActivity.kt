package com.lkb.baseandroidproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lkb.baseandroidproject.ui.theme.BaseAndroidProjectTheme

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAndroidProjectTheme {
                emptyBox()
            }
        }

    }

    fun showToast() {
        Toast.makeText(this@BaseActivity, "error happen", Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar() {

    }

    @Composable
    fun emptyBox() {
        return Box(modifier = Modifier.fillMaxSize()){
            Text(text = "Hello")
        }
    }
}

