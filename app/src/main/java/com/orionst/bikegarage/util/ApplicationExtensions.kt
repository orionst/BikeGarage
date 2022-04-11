package com.orionst.bikegarage.util

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.FragmentActivity

tailrec fun Context.getActivity(): FragmentActivity = when (this) {
    is FragmentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> throw NoSuchElementException("Context does not provide activity")
}

fun Context.getApp(): Application = getActivity().application.tryCast()
