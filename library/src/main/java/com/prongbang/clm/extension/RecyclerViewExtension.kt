package com.prongbang.clm.extension

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.triggerScroll(x: Int = 2, y: Int = 0, delayMillis: Long = 100) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({ scrollBy(x, y) }, delayMillis)
}

fun RecyclerView.pagerSnapper() {
    PagerSnapHelper().attachToRecyclerView(this)
}

fun RecyclerView?.getCurrentPosition() : Int {
    return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
}