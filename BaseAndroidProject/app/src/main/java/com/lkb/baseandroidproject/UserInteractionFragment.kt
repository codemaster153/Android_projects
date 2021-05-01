package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.user_interaction_layout.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class UserInteractionFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()

    //private var user: User? = null
    private var localTransaction = mutableListOf<Transaction>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mView = inflater.inflate(R.layout.user_interaction_layout, null)
        mView.tvUserName.text = viewModel.selectedUser?.name
        mView.tvUserLimit.text = viewModel.selectedUser?.limit.toString()
        mView.tvAddress.text = viewModel.selectedUser?.address
        mView.btnViewAllTransaction.setOnClickListener{
            viewModel.inValidateData()
            (activity as MainActivity).startViewAllTransactionFragment(TransactionDetailsFragment.getInstance())
        }
        mView.btnNewCredit.setOnClickListener {
            update(
                viewModel.selectedUser,
                mView.editTextDate.text.toString(),
                -mView.etTransactionAmount.text.toString().toDouble()
            )

        }
        mView.btnAddCollection.setOnClickListener {
            update(
                viewModel.selectedUser,
                mView.editTextDate.text.toString(),
                mView.etTransactionAmount.text.toString().toDouble()
            )
        }
        return mView
    }

    private fun createNewTransaction(timeStr: String, amount: Double): Transaction {
        var time: Long = 0
        if (timeStr.isNotEmpty()) {
            time = LocalDate.parse(
                timeStr,
                DateTimeFormatter.ofPattern("dd-MM-uuuu")
            )
                .atStartOfDay(
                    ZoneId.of("Asia/Kolkata")  // Or use `ZoneOffset.UTC` instead of a zone.
                )
                .toInstant()
                .toEpochMilli()
        } else {
            time = Util.getCurrentTimeEpoch()
        }
        return Transaction(amount, time)
    }

    private fun update(user: User?, timeStr: String, amount: Double) {
        user?.let {
            viewModel.updateUser(it, createNewTransaction(timeStr, amount))
                .observe(viewLifecycleOwner, { result ->
                    if (result.contentEquals("Success")) {
                        Toast.makeText(context, "Transaction Successful ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Transaction Failed", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }


    companion object {
        fun getInstance(): UserInteractionFragment {
            return UserInteractionFragment()
        }

    }
}