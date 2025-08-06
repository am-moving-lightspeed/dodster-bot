package com.github.am_moving_lightspeed.tg.bot.dodster

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.BotModule
import com.github.am_moving_lightspeed.tg.bot.dodster.config.ConfigModule
import com.google.inject.Guice
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.generics.LongPollingBot

fun main(args: Array<String>) {
    val injector = Guice.createInjector(
        BotModule(),
        ConfigModule(args)
    )
    injector
        .getInstance(TelegramBotsApi::class.java)
        .registerBot(
            injector.getInstance(LongPollingBot::class.java)
        )
}
