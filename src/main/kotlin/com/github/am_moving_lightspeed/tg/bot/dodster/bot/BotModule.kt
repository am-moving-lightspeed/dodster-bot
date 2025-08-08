package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.StateChangeProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.UpdateProcessorChain
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.HelpCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.SettingsCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command.StartCommandProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import com.github.am_moving_lightspeed.tg.bot.dodster.config.ApplicationProperties
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
        newSetBinder(binder(), UpdateProcessorChain::class.java).apply {
            addBinding().to(HelpCommandProcessor::class.java)
            addBinding().to(SettingsCommandProcessor::class.java)
            addBinding().to(StartCommandProcessor::class.java)
            addBinding().to(StateChangeProcessor::class.java)
        }
    }

    @Provides
    @Singleton
    @ConfiguredProcessorChainHead
    fun updateProcessorChain(
        processors: Set<UpdateProcessorChain>
    ): UpdateProcessorChain {
        val head: UpdateProcessorChain = processors.first()
        var previous: UpdateProcessorChain? = null

        for (processor in processors) {
            previous?.next = processor
            previous = processor
        }
        return head
    }

    @Provides
    @Singleton
    fun stateManager(
        @ApplicationProperties properties: Properties
    ): StateManager = StateManager(properties)

    @Provides
    @Singleton
    fun dodsterBot(
        @ConfiguredProcessorChainHead processorsChain: UpdateProcessorChain,
        @ApplicationProperties properties: Properties
    ): TelegramLongPollingBot = DodsterBot(processorsChain, properties)

    @Provides
    @Singleton
    fun telegramBotsApi(): TelegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
}
