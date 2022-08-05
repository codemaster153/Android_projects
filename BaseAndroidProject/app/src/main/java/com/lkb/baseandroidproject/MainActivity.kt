package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var ss: String
    lateinit var viewModel:MainViewModel
    //private val viewModel: MainViewModel by viewModels()

    //    @Inject
//    @FirebaseRepo
//    lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        this::ss.isInitialized
        viewModel.saveData()
        // userRepository.saveUserData("lalit", "1234")
    }
}
