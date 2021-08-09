package com.lkb.baseandroidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item.view.*

class DummyAdapter(val width: Int, val height: Int) :
    BaseRecyclerAdapter<DummyAdapter.MyViewHolder, String>() {
    class MyViewHolder(val myView: View) : RecyclerView.ViewHolder(myView)

    override fun onCreateChildViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_item, viewGroup, false)
        return MyViewHolder(rootView)
    }

    override fun onBindChildViewHolder(holder: MyViewHolder, position: Int) {
        holder.myView.singleItem.text = recyclerData[position]
    }

}