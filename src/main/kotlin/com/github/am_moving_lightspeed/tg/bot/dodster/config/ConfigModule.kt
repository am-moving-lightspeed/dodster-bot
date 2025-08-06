package com.github.am_moving_lightspeed.tg.bot.dodster.config

import com.github.am_moving_lightspeed.tg.bot.dodster.util.APPLICATION_PROPERTIES_FILE
import com.github.am_moving_lightspeed.tg.bot.dodster.util.DASH
import com.github.am_moving_lightspeed.tg.bot.dodster.util.EQ
import com.github.am_moving_lightspeed.tg.bot.dodster.util.second
import com.google.inject.AbstractModule
import com.google.inject.name.Names.bindProperties
import java.io.InputStream
import java.util.Properties

class ConfigModule(private val programArgs: Array<String>): AbstractModule() {

    private val applicationPropertiesInputStream: InputStream
        get() = javaClass.classLoader.getResourceAsStream(APPLICATION_PROPERTIES_FILE)
                ?: throw IllegalStateException("Application properties file not found")

    override fun configure() {
        prepareApplicationProperties(programArgs).also {
            bindProperties(binder(), it)
        }
    }

    private fun prepareApplicationProperties(cmdLineArgs: Array<String>): Properties =
        Properties().apply {
            applicationPropertiesInputStream.use {
                load(it)
            }
            cmdLineArgs
                .filter { it.contains(EQ) }
                .map { it.removePrefix(DASH).split(EQ) }
                .forEach { setProperty(it.first(), it.second()) }
        }
}
