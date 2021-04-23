package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import org.koin.android.viewmodel.getViewModelStore

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.d("ViewModel", "onCreate viewModel initialized $viewModel")
        binding.textView3.text = viewModel.userName
        binding.button2.setOnClickListener {
            viewModel.userName = "Hello"
        }
    }
}
