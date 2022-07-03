package com.prongbang.clm

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

open class CenterDownLayoutManager : LinearLayoutManager {

    private var shrinkAmount: Float = 0.12f
    private var shrinkDistance: Float = 0.5f
    private var pixelSpace: Float = -1f
    private var percentSpace: Float = -1f
    private var scaleDownPercentWidth: Float = 20f

    constructor(context: Context?) : super(context)

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean,
        shrinkAmount: Float = 0.12f,
        shrinkDistance: Float = 0.5f,
        pixelSpace: Float = -1f,
        percentSpace: Float = -1f,
        scaleDownPercentWidth: Float = 20f,
    ) : super(context, orientation, reverseLayout) {
        this.shrinkAmount = shrinkAmount
        this.shrinkDistance = shrinkDistance
        this.pixelSpace = pixelSpace
        this.percentSpace = percentSpace
        this.scaleDownPercentWidth = scaleDownPercentWidth
    }

    constructor(
        context: Context?,
        shrinkAmount: Float = 0.12f,
        shrinkDistance: Float = 0.5f,
        pixelSpace: Float = -1f,
        percentSpace: Float = -1f,
        scaleDownPercentWidth: Float = 20f,
    ) : super(context) {
        this.shrinkAmount = shrinkAmount
        this.shrinkDistance = shrinkDistance
        this.pixelSpace = pixelSpace
        this.percentSpace = percentSpace
        this.scaleDownPercentWidth = scaleDownPercentWidth
    }

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        // Force height of viewHolder here, this will override layout_width from xml
        lp.width = width - (width.toFloat() * scaleDownPercentWidth / 100f).toInt()
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation: Int = orientation
        return if (orientation == VERTICAL) {
            val scrolled: Int = super.scrollVerticallyBy(dy, recycler, state)
            val midpoint = height / 2f
            val d0 = 0f
            val d1 = shrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - shrinkAmount
            for (i in 0 until childCount) {
                val child: View = getChildAt(i) ?: continue

                val childMidpoint: Float = (getDecoratedBottom(child) + getDecoratedTop(child)) / 2f
                val d = min(d1, abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            scrolled
        } else {
            0
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation: Int = orientation
        return if (orientation == HORIZONTAL) {
            val scrolled: Int = super.scrollHorizontallyBy(dx, recycler, state)
            val midpoint = width / 2f
            val d0 = 0f
            val d1 = shrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - shrinkAmount
            for (i in 0 until childCount) {
                val child: View = getChildAt(i) ?: continue

                val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                val d = min(d1, abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = 0.88f

                // Set aligned
                if (scale < 0.9) {
                    // Keeping it aligned at the bottom.
                    if (percentSpace > -1) {
                        child.pivotY = child.height.toFloat()
                    } else if (pixelSpace > -1) {
                        child.translationY = 0f
                    }
                } else {
                    // Keeping it aligned at the top.
                    if (percentSpace > -1) {
                        child.pivotY = child.height.toFloat() - (child.height * percentSpace / 100)
                    } else if (pixelSpace > -1) {
                        child.translationY = pixelSpace
                    }
                }
            }
            scrolled
        } else {
            0
        }
    }
}