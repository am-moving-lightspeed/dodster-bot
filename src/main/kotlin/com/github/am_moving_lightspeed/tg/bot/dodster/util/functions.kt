package com.github.am_moving_lightspeed.tg.bot.dodster.util

// Collections utils
fun <T> List<T>.second(): T =
    if (size >= 2) get(1) else throw NoSuchElementException()
