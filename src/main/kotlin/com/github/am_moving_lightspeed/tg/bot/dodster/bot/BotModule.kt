package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.config.BotProperties
import com.google.inject.AbstractModule
import com.google.inject.Provides
import java.util.Properties
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.generics.LongPollingBot
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class BotModule: AbstractModule() {

    @Provides
    fun bot(@BotProperties properties: Properties): LongPollingBot = Bot(properties)

    @Provides
    fun telegramBotsApi(): TelegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
}
