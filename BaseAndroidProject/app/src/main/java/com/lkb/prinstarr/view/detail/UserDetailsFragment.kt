package com.lkb.prinstarr.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkb.prinstarr.R
import com.lkb.prinstarr.User
import com.lkb.prinstarr.view.MainActivity
import com.lkb.prinstarr.view.MainViewModel
import kotlinx.android.synthetic.main.user_details_layout.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserDetailsFragment : Fragment(), UserDataAdapter.OnItemClickListener {
    var list: List<User> = mutableListOf()
    private val viewModel by sharedViewModel<MainViewModel>()
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
            this.adapter = UserDataAdapter(listOf(), this@UserDetailsFragment)
            this.layoutManager = layoutManager
        }
        updateRecyclerViewData(recyclerView)
        return mView
    }

    private fun updateRecyclerViewData(recyclerView: RecyclerView) {
        viewModel.getUserData().observe(viewLifecycleOwner,
            { data ->
                (recyclerView.adapter as UserDataAdapter).updateData(data); this.list = data
            })
    }

    companion object {
        fun getInstance(): UserDetailsFragment {
            return UserDetailsFragment()
        }

    }

    override fun onListItemClick(position: Int) {
        (activity as MainActivity).startUserTransactionFragment(list[position])
        viewModel.selectedUser = list[position]
        viewModel.selectedUserUUID = list[position].uuid.toString()
    }
}