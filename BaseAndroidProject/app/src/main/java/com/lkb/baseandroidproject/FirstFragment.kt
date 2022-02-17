package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.first_frag, null)
        return view
    }

    companion object {
        fun newInstance(position: Int): FirstFragment {
            return FirstFragment()
        }
    }
}