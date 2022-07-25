package com.prongbang.clmexample.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prongbang.clmexample.Card
import com.prongbang.clmexample.databinding.ItemCardBinding

class MainViewHolder(
    private val binding: ItemCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Card) {
        binding.apply {
            numberText.text = "${item.id}"
        }
    }

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