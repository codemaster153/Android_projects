package com.lkb.prinstarr.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lkb.prinstarr.R
import com.lkb.prinstarr.view.MainViewModel
import kotlinx.android.synthetic.main.create_user_layout.*
import kotlinx.android.synthetic.main.create_user_layout.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreateUserFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.create_user_layout, null)
        val createUser = rootView.btnCreateUser
        createUser.setOnClickListener {
            if (rootView.editTextTextPersonName.text.toString().isEmpty()) {
                Toast.makeText(context, "Name is Mandatory", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (rootView.etPhoneNumber.text.toString().isEmpty()) {
                Toast.makeText(context, "Phone Number is Mandatory", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.createUser(
                rootView.editTextTextPersonName.text.toString(),
                rootView.etAddress.text.toString(),
                rootView.etPhoneNumber.text.toString(),
                rootView.etCreditLimit.text.toString().toDouble()
            ).observe(viewLifecycleOwner, {
                if (it.contentEquals("Success")) {
                    viewModel.inValidateData()
                    Toast.makeText(context, "User Created ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "User Not created ", Toast.LENGTH_SHORT).show()
                }
            })
        }
        return rootView
    }

    companion object {
        fun getInstance(): CreateUserFragment {
            return CreateUserFragment()
        }

    }
}