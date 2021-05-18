package com.lkb.prinstarr.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lkb.prinstarr.R
import com.lkb.prinstarr.view.login.LoginViewModel


class BottomDialog : BottomSheetDialogFragment() {
    lateinit var configUrl: EditText
    lateinit var viewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.bottom_sheet_layout, null)
        configUrl = rootView.findViewById(R.id.etConfigUrl)
        configUrl?.setText((viewModel as LoginViewModel).getPrefValue("config"))
        //for testing
        configUrl.setText("https://bluesky15.github.io/prinstar/testConfig.json")
        return rootView
    }

    companion object {
        fun getInstance(viewModel: ViewModel): BottomDialog {
            val fragment = BottomDialog()
            fragment.viewModel = viewModel
            return fragment
        }
    }


    override fun onDismiss(dialog: DialogInterface) {
        (viewModel as LoginViewModel).createPreference("config", configUrl?.text.toString())
        super.onDismiss(dialog)
    }
}