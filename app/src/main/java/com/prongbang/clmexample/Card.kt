package com.prongbang.clmexample

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(val id: Int) : Parcelable
