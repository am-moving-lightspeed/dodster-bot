package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_NAME_PROP
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_TOKEN_PROP
import com.google.inject.AbstractModule
import com.google.inject.Provides
import jakarta.inject.Named
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.generics.LongPollingBot
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class BotModule: AbstractModule() {

    @Provides
    fun bot(
        @Named(BOT_NAME_PROP) name: String,
        @Named(BOT_TOKEN_PROP) token: String
    ): LongPollingBot = Bot(name, token)

    @Provides
    fun telegramBotsApi(): TelegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
}
