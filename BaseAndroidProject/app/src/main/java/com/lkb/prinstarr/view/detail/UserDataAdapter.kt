package com.lkb.prinstarr.view.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lkb.prinstarr.R
import com.lkb.prinstarr.User

class UserDataAdapter(private var dataSet: List<User>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onListItemClick(position: Int)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootView = view
        val userName: TextView = view.findViewById(R.id.tvUserName)
        val userAddress: TextView = view.findViewById(R.id.tvAddress)
        val creditLimit: TextView = view.findViewById(R.id.tvCreditLimitValue)
        val contact: TextView = view.findViewById(R.id.tvContact)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = dataSet[position].name
        holder.userAddress.text = dataSet[position].address
        holder.contact.text = dataSet[position].contact
        holder.creditLimit.text = dataSet[position].limit.toString()
        holder.rootView.setOnClickListener { listener.onListItemClick(position) }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(list:List<User>){
        dataSet = list
        notifyDataSetChanged()
    }


}
