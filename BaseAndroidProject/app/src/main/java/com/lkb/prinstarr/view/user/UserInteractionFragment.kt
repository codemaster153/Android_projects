package com.lkb.prinstarr.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lkb.prinstarr.*
import com.lkb.prinstarr.Util.Companion.convertStrToEpoch
import com.lkb.prinstarr.view.MainActivity
import com.lkb.prinstarr.view.MainViewModel
import kotlinx.android.synthetic.main.user_interaction_layout.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserInteractionFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mView = inflater.inflate(R.layout.user_interaction_layout, null)
        mView.tvUserName.text = viewModel.selectedUser?.name
        mView.tvUserLimit.text = viewModel.selectedUser?.limit.toString()
        mView.tvAddress.text = viewModel.selectedUser?.address
        mView.btnViewAllTransaction.setOnClickListener {
            viewModel.inValidateData()
            (activity as MainActivity).startViewAllTransactionFragment(TransactionDetailsFragment.getInstance())
        }
        mView.btnNewCredit.setOnClickListener {
            if(mView.etCreditDays.text.toString().isEmpty())
                mView.etCreditDays.setText("30")
            when {
                mView.etTransactionAmount.text.toString().isEmpty() -> {
                    context?.let { it1 ->
                        toast(
                            it1,
                            "Amount should not be Empty"
                        )
                    }
                }
                mView.editTextDate.text.toString().isEmpty() -> {
                    update(
                        viewModel.selectedUser,
                        mView.editTextDate.text.toString(),
                        -mView.etTransactionAmount.text.toString().toDouble(),
                        mView.etCreditDays.text.toString().toInt()
                    )
                }
                Util.isValidDate(mView.editTextDate.text.toString()) -> {
                    update(
                        viewModel.selectedUser,
                        mView.editTextDate.text.toString(),
                        -mView.etTransactionAmount.text.toString().toDouble(),
                        mView.etCreditDays.text.toString().toInt()
                    )
                }
                else -> {
                    context?.let { it1 ->
                        toast(
                            it1,
                            "Please enter valid date in dd-mm-yyyy format"
                        )
                    }
                }
            }

        }
        mView.btnAddCollection.setOnClickListener {
            when {
                mView.etTransactionAmount.text.toString().isEmpty() -> {
                    context?.let { it1 ->
                        toast(
                            it1,
                            "Amount should not be Empty"
                        )
                    }
                }
              mView.editTextDate.text.toString().isEmpty() -> {
                    update(
                        viewModel.selectedUser,
                        mView.editTextDate.text.toString(),
                        mView.etTransactionAmount.text.toString().toDouble(),
                        mView.etCreditDays.text.toString().toInt()
                    )
                }
                Util.isValidDate(mView.editTextDate.text.toString()) -> {
                    update(
                        viewModel.selectedUser,
                        mView.editTextDate.text.toString(),
                        mView.etTransactionAmount.text.toString().toDouble(),
                        mView.etCreditDays.text.toString().toInt()
                    )
                }
                else -> {
                    context?.let { it1 ->
                        toast(
                            it1,
                            "Please enter valid date in dd-mm-yyyy format"
                        )
                    }
                }
            }

        }
        return mView
    }

    private fun createNewTransaction(timeStr: String, amount: Double, creditDays: Int): Transaction {
        var time: Long = convertStrToEpoch(timeStr)
        return Transaction(amount, time,false,"",creditDays)
    }



    private fun update(user: User?, timeStr: String, amount: Double, creditDays:Int) {
        user?.let {
            viewModel.updateUser(it, createNewTransaction(timeStr, amount, creditDays))
                .observe(viewLifecycleOwner) { result ->
                    if (result.contentEquals("Success")) {
                        Toast.makeText(context, "Transaction Successful ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Transaction Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    companion object {
        fun getInstance(): UserInteractionFragment {
            return UserInteractionFragment()
        }

    }
}