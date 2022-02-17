package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<ViewPager2>(R.id.doppelgangerViewPager)
        val viewPagerAdapter = ViewPagerAdapter(this, 4)
        view.adapter = viewPagerAdapter
    }
}
