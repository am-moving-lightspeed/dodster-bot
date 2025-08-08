package com.github.am_moving_lightspeed.tg.bot.dodster.config

import com.github.am_moving_lightspeed.tg.bot.dodster.util.APPLICATION_PROPERTIES_FILE
import com.github.am_moving_lightspeed.tg.bot.dodster.util.DASH
import com.github.am_moving_lightspeed.tg.bot.dodster.util.EQ
import com.github.am_moving_lightspeed.tg.bot.dodster.util.second
import com.google.inject.AbstractModule
import com.google.inject.name.Names.bindProperties
import java.io.InputStreamReader
import java.io.Reader
import java.util.Properties
import kotlin.text.Charsets.UTF_8

class ApplicationPropertiesModule(private val programArgs: Array<String>): AbstractModule() {

    override fun configure() {
        prepareApplicationProperties(programArgs).also {
            bindProperties(binder(), it)
            bind(Properties::class.java)
                .annotatedWith(ApplicationProperties::class.java)
                .toInstance(it)
        }
    }

    private fun prepareApplicationProperties(programArgs: Array<String>): Properties =
        Properties().apply {
            getApplicationPropertiesReader().use {
                load(it)
            }
            programArgs
                .filter { it.contains(EQ) }
                .map { it.removePrefix(DASH).split(EQ) }
                .forEach { setProperty(it.first(), it.second()) }
        }

    private fun getApplicationPropertiesReader(): Reader {
        val inputStream = javaClass.classLoader.getResourceAsStream(APPLICATION_PROPERTIES_FILE)
            ?: throw IllegalStateException("Application properties file not found")
        return InputStreamReader(inputStream, UTF_8)
    }
}
