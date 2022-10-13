package com.lkb.demo

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.lkb.demo.databinding.ActivityMainBinding
import com.lkb.demo.ui.main.adapter.MainAdapter
import com.lkb.demo.ui.main.intent.MainIntent
import com.lkb.demo.ui.main.viewmodel.MainViewModel
import com.lkb.demo.ui.main.viewstate.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        observeViewModels()
        lifecycleScope.launch{
            viewModel.mainIntent.send(MainIntent.GetPosts)
        }
    }
    private fun initView(){
        binding.rvPosts.adapter = mainAdapter
    }

    private fun observeViewModels(){
        lifecycleScope.launch{
            viewModel.state.collect{

                when(it){
                    is MainViewState.Loading->{binding.progressBar.visibility = View.VISIBLE}
                    is MainViewState.Success->{binding.progressBar.visibility = View.GONE
                    mainAdapter.addItems(it.data)
                    }
                    is MainViewState.Error->{binding.progressBar.visibility = View.GONE

                    }
                }
            }
        }
    }

}