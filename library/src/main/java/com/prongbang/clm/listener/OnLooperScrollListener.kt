package com.prongbang.clm.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

abstract class OnLooperScrollListener : RecyclerView.OnScrollListener() {

    private var isInitializer = true

    abstract fun getItemCount(): Int

    open fun getScrollDistanceOfColumnClosestToLeft(recyclerView: RecyclerView): Int {
        val manager = recyclerView.layoutManager as LinearLayoutManager? ?: return 0
        val firstVisibleColumnViewHolder = recyclerView.findViewHolderForAdapterPosition(
            manager.findFirstVisibleItemPosition()
        ) ?: return 0
        val columnWidth = firstVisibleColumnViewHolder.itemView.measuredWidth
        val left = firstVisibleColumnViewHolder.itemView.left
        val absoluteLeft = abs(left)

        return if (absoluteLeft <= columnWidth / 2) left else columnWidth - absoluteLeft
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstItemVisible: Int = layoutManager.findFirstVisibleItemPosition()

        if (firstItemVisible != 1 && (firstItemVisible % getItemCount() == 1)) {
            layoutManager.scrollToPosition(1)
        } else if (firstItemVisible != 1 && firstItemVisible > getItemCount() && (firstItemVisible % getItemCount() > 1)) {
            layoutManager.scrollToPosition(firstItemVisible % getItemCount())
        } else if (firstItemVisible == 0) {
            // Scroll distance of column closest to left
            if (isInitializer) {
                isInitializer = false
                val scrollDistance = getScrollDistanceOfColumnClosestToLeft(recyclerView)
                if (scrollDistance != 0) {
                    recyclerView.smoothScrollBy(scrollDistance, 0, null, 1)
                }
            }

            val scrollOffset = recyclerView.computeHorizontalScrollOffset()
            layoutManager.scrollToPositionWithOffset(getItemCount(), -scrollOffset)
        }
    }
}