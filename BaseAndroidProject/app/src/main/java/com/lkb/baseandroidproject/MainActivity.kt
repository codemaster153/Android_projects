package com.lkb.baseandroidproject

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel
    lateinit var disposable: Disposable
    lateinit var wikiDisposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val clickListener = View.OnClickListener{
            when(it.id){
                R.id.searchButton->{
                    val searchTextString = binding.searchText.text.toString()
                    wikiDisposable = mViewModel.callHitCount(searchTextString)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            { result -> binding.hitCountText.text = "${result.query.searchinfo.totalhits} result found" },
                            { error -> Log.i("error", error.toString())
                            }
                        )
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
