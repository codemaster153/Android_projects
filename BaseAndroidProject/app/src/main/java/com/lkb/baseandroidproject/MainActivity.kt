package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    lateinit var disposable: Disposable
    lateinit var wikiDisposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //mViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        val clickListener = View.OnClickListener {
            when (it.id) {
                R.id.searchButton -> {
                    lifecycleScope.launch {
                        val searchTextString = binding.searchText.text.toString()
                        wikiDisposable = mViewModel.callHitCount(searchTextString)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                { result ->
                                    binding.hitCountText.text =
                                        "${result.query.searchinfo.totalhits} result found"
                                },
                                { error ->
                                    Log.i("error", error.toString())
                                }
                            )
                    }
                }
            }
        }
        binding.searchButton.setOnClickListener(clickListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
