package com.prongbang.clmexample.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prongbang.clmexample.Card

class MainAdapter : ListAdapter<Card, MainViewHolder>(DIFF_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

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