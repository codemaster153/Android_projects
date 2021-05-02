package com.lkb.prinstarr.view.user

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lkb.prinstarr.R
import com.lkb.prinstarr.Transaction
import com.lkb.prinstarr.Util

class TransactionDataAdapter(private var dataSet: List<Transaction>) :
    RecyclerView.Adapter<TransactionDataAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionType: TextView = view.findViewById(R.id.tvTransactionType)
        val transactionDate: TextView = view.findViewById(R.id.tvDate)
        val amount: TextView = view.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_items, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.transactionDate.text = Util.fromEpochToDate(dataSet[position].date)
        if(dataSet[position].amount<0){
            holder.transactionType.setTextColor(Color.RED)
        }else{
            holder.transactionType.setTextColor(Color.GREEN)
        }
        holder.amount.text = Util.absAmount(dataSet[position].amount)
        holder.transactionType.text = if (dataSet[position].amount < 0) "SALE" else "PAID"
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(data: List<Transaction>?) {
        if (data != null) {
            dataSet = data
            notifyDataSetChanged()
        }
    }

}
