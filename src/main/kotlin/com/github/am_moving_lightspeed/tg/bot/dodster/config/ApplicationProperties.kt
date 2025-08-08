package com.github.am_moving_lightspeed.tg.bot.dodster.config

import jakarta.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

@Retention(RUNTIME)
@Target(FIELD, FUNCTION, VALUE_PARAMETER)
@Qualifier
annotation class ApplicationProperties
