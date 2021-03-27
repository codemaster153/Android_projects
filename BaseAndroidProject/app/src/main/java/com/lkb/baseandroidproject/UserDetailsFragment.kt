package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.user_details_layout.view.*

class UserDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mView = inflater.inflate(R.layout.user_details_layout, null)
        val recyclerView = mView.recyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        with(recyclerView) {
            this.adapter = UserDataAdapter(listOf())
            this.layoutManager = layoutManager
        }
        val list = mutableListOf<User>()
        val database = Firebase.database.reference
        database.child(Constants.DB_NAME).get().addOnSuccessListener { it ->
            //it.children.forEach { e->e.getValue(User::class.java) }
            val iterator = it.children
            iterator.forEach {
                it.getValue(User::class.java)?.let { it1 -> list.add(it1) }
            }
//            while (iterator.hasNext()) {
//                iterator.next().getValue(User::class.java)?.let { it1 -> list.add(it1) }
//            }
            recyclerView.adapter = UserDataAdapter(list)
            (recyclerView.adapter as UserDataAdapter).notifyDataSetChanged()
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mView
    }

    companion object {
        fun getInstance(): UserDetailsFragment {
            return UserDetailsFragment()
        }

    }
}