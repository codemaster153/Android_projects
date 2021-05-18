package com.lkb.prinstarr.demo

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import com.lkb.prinstarr.R
import com.lkb.prinstarr.view.BaseActivity

class DemoActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_layout)
        val editTextTextPersonName2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val datePicker2 = findViewById<DatePicker>(R.id.datePicker2)
        editTextTextPersonName2.setOnClickListener {
            datePicker2.visibility = View.VISIBLE
        }
//        datePicker2.setOnDateChangedListener(object:OnDateChangedListener{
//
//        })
    }
}