package com.lkb.demo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lkb.demo.data.model.FakeDTO
import com.lkb.demo.databinding.ViewHolderMainBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private val list = mutableListOf<FakeDTO>()
    fun addItems(list: List<FakeDTO>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderMainBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.viewDataBinding
        val item = this.list[position]
        binding.title.text = item.title
        binding.message.text = item.body

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}