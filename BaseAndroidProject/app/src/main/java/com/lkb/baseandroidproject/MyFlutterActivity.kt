package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MyFlutterActivity : FlutterActivity() {
    private val CHANNEL = "MyNativeChannel"
//    private lateinit var binding: ActivityMainBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        binding.btnFlutterScreen.setOnClickListener {
//            startActivity(
//                FlutterActivity.createDefaultIntent(this)
//            )
//        }
////        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))
////
////        MethodChannel(
////            flutterEngine?.dartExecutor?.binaryMessenger,
////            CHANNEL
////        ).setMethodCallHandler { call, result ->
////            if (call.method.equals("getData")) {
////                result.success(getData())
////            } else {
////                result.notImplemented()
////            }
////        }
//    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method.equals("getData")) {
                val batteryLevel: Int = getData()
                if (batteryLevel != -1) {
                    result.success(batteryLevel)
                } else {
                    result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun getData(): Int {
        return 10
    }
}