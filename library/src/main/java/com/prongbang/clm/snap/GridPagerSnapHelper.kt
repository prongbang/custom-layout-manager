package com.prongbang.clm.snap

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class GridPagerSnapHelper : PagerSnapHelper() {
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val forwardDirection = if (layoutManager?.canScrollHorizontally() == true) {
            velocityX > 0
        } else {
            velocityY > 0
        }
        val centerPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
        return centerPosition +
                if (forwardDirection) (layoutManager as GridLayoutManager).spanCount - 1 else 0
    }
}