package com.github.am_moving_lightspeed.tg.bot.dodster.config

import com.github.am_moving_lightspeed.tg.bot.dodster.util.APPLICATION_PROPERTIES_FILE
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_PROP_PREFIX
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
        val applicationProperties = prepareApplicationProperties(programArgs).also {
            bindProperties(binder(), it)
        }
        extractBotProperties(applicationProperties).also {
            bind(Properties::class.java)
                .annotatedWith(BotProperties::class.java)
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

    private fun extractBotProperties(applicationProperties: Properties): Properties =
        Properties().apply {
            applicationProperties
                .filterKeys {
                    (it as String).startsWith(BOT_PROP_PREFIX)
                }
                .forEach {
                    setProperty(it.key as String, it.value as String)
                }
        }
}
