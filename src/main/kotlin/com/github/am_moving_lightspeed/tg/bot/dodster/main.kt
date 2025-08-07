package com.github.am_moving_lightspeed.tg.bot.dodster

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.BotModule
import com.github.am_moving_lightspeed.tg.bot.dodster.config.ApplicationPropertiesModule
import com.google.inject.Guice
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi

/**
 * @param args program arguments that are expected in the following format: `-prog.arg=value`
 */
fun main(args: Array<String>) {
    val injector = Guice.createInjector(
        ApplicationPropertiesModule(args),
        BotModule()
    )
    injector
        .getInstance(TelegramBotsApi::class.java)
        .registerBot(
            injector.getInstance(TelegramLongPollingBot::class.java)
        )
}
