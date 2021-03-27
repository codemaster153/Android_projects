package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.create_user_layout.*
import kotlinx.android.synthetic.main.create_user_layout.view.*

class CreateUserFragment : Fragment() {
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = Firebase.database.reference
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
            val user = User(
                rootView.editTextTextPersonName.text.toString(),
                rootView.etAddress.text.toString(),
                rootView.etPhoneNumber.text.toString(),
                rootView.etCreditLimit.text.toString().toDouble(),
                null
            )
            val uuid = Util.generateUserId(
                rootView.etPhoneNumber.text.toString().toLong(),
                user.name!!
            )
            user.uuid = uuid
            database.child(Constants.DB_NAME).child(uuid)
                .setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(context, "User Created ", Toast.LENGTH_SHORT).show()
                    //refresh the fragment ui
                    rootView.editTextTextPersonName.setText("")
                    rootView.etAddress.setText("")
                    rootView.etPhoneNumber.setText("")
                    rootView.etCreditLimit.setText("")

                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "User Not created ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
        return rootView
    }

    companion object {
        fun getInstance(): CreateUserFragment {
            return CreateUserFragment()
        }

    }
}