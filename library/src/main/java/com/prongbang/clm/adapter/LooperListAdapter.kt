package com.prongbang.clm.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prongbang.clm.listener.OnLooperScrollListener

abstract class LooperListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    override fun getItemCount(): Int = super.getItemCount() * 2

    open fun getPosition(position: Int): Int = position % super.getItemCount()

    open fun getItemCountOriginal(): Int = super.getItemCount()

    open fun addOnLooperScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : OnLooperScrollListener() {
            override fun getItemCount(): Int = getItemCountOriginal()
        })
    }
}