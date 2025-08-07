package com.github.am_moving_lightspeed.tg.bot.dodster.util

// Collections utils
fun <T> List<T>.second(): T =
    if (size >= 2) get(1) else throw NoSuchElementException()

// Object utils
fun <T, R> T?.letOrThrow(function: (T) -> R): R {
    checkNotNull(this)
    return function(this)
}

fun <T, R> T?.runOrThrow(block: T.() -> R): R {
    checkNotNull(this)
    return block(this)
}
