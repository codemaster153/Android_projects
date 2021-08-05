package com.lkb.prinstarr.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lkb.prinstarr.R
import com.lkb.prinstarr.view.MainViewModel
import kotlinx.android.synthetic.main.transaction_detail_layout.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TransactionDetailsFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.transaction_detail_layout, null)
        val tvUserNameField = rootView.findViewById<TextView>(R.id.tvUserNameField)
        val recyclerView = rootView.transactionRecyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        with(recyclerView) {
            this.adapter = TransactionDataAdapter(listOf())
            this.layoutManager = layoutManager
        }
        viewModel.getUserData().observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                viewModel.getAllTransactionData().observe(viewLifecycleOwner,
                    { transactions ->
                        (recyclerView.adapter as TransactionDataAdapter).updateData(
                            transactions
                        )
                        tvUserNameField.text = viewModel?.selectedUser?.name ?:"NA"
                    })
        })

        return rootView
    }

    companion object {
        fun getInstance(): TransactionDetailsFragment {
            return TransactionDetailsFragment()
        }

    }
}