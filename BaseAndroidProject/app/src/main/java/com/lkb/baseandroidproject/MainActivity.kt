package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var ss: String
    @Inject
    @FirebaseRepo
    lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       this::ss.isInitialized
        userRepository.saveUserData("lalit", "1234")
    }
}
