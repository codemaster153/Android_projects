package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
   val mViewModel: MainViewModel by viewModels()
    lateinit var disposable: Disposable
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = ""
        disposable = mViewModel.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("LKB-","${Thread.currentThread().name}")
                binding.textView.text = it.toString();
            },
                { error -> binding.textView.text = error.localizedMessage },
                { binding.textView.text = "completed" }
            )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
