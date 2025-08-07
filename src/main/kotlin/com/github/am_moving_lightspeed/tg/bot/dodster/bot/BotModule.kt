package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.UpdateProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.HelpCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.SettingsCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.StartCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import com.github.am_moving_lightspeed.tg.bot.dodster.config.BotProperties
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.multibindings.Multibinder.newSetBinder
import jakarta.inject.Singleton
import java.util.Properties
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class BotModule: AbstractModule() {

    override fun configure() {
        newSetBinder(binder(), UpdateProcessor::class.java).apply {
            addBinding().to(HelpCommandProcessor::class.java)
            addBinding().to(SettingsCommandProcessor::class.java)
            addBinding().to(StartCommandProcessor::class.java)
        }
    }

    @Provides
    @Singleton
    fun stateManager(): StateManager = StateManager()

    @Provides
    @Singleton
    fun dodsterBot(
        processors: Set<UpdateProcessor>,
        @BotProperties properties: Properties
    ): TelegramLongPollingBot = DodsterBot(processors, properties)

    @Provides
    @Singleton
    fun telegramBotsApi(): TelegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
}
