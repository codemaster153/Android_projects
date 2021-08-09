package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.d("PIXEL", "height is $height and Width is $width")
        val viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            adapter = DummyAdapter(width-(32+4*8), height)
            (adapter as DummyAdapter).recyclerData = mutableListOf("hello", "hi")
            layoutManager = viewManager
        }
    }
}
