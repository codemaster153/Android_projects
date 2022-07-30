package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    fun getData(): Observable<String> {
        //return Observable.just("hello", "hi", "there").delay(3000, TimeUnit.MILLISECONDS)
        return Observable.range(1,100)
            .flatMap {
                Log.d("LKB-","viewmodel - ${Thread.currentThread().name}")
                Thread.sleep(1000)
                Observable.just(it.toString())
            }

    }

}