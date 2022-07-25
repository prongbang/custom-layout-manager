package com.prongbang.clmexample.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.prongbang.clm.adapter.LooperListAdapter
import com.prongbang.clmexample.Card

class MainAdapter : LooperListAdapter<Card, MainViewHolder>(DIFF_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val newPosition = getPosition(position)
        holder.bind(getItem(newPosition))
    }

    companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}