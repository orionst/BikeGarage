package com.orionst.bikegarage.util

inline fun <reified T> Any?.tryCast(): T =
    this as T

inline fun <reified T> Any?.tryCastOrNull(): T? =
    this as? T
