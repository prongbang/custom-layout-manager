package com.prongbang.clmexample.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prongbang.clmexample.databinding.ItemCardBinding

class MainViewHolder(
    private val binding: ItemCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): MainViewHolder {
            return MainViewHolder(
                ItemCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}