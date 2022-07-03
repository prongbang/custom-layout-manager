package com.prongbang.clm.extension

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.triggerScroll(x: Int = 2, y: Int = 0, delayMillis: Long = 100) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({ scrollBy(x, y) }, delayMillis)
}