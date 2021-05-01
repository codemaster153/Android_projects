package com.lkb.baseandroidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder.transactionDate.text = dataSet[position].date.toString()
        holder.amount.text = dataSet[position].amount.toString()
        holder.transactionType.text = if (dataSet[position].amount < 0) "Sale" else "Repay"
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
