package com.lkb.baseandroidproject

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var count = 0
    var recyclerData = mutableListOf<T>()
        set(newItems) {
            val diffCallback = DiffCallback(newItems, field)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newItems
            count = recyclerData.size
            diffResult.dispatchUpdatesTo(this)
        }

    abstract fun onCreateChildViewHolder(viewGroup: ViewGroup, viewType: Int): VH

    abstract fun onBindChildViewHolder(holder: VH, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateChildViewHolder(parent, viewType)
    }
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindChildViewHolder(holder as VH, position)
    }

    override fun getItemCount(): Int {
        return recyclerData.size
    }
}

private class DiffCallback<T>(private val newList: List<T>, private val oldList: List<T>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}