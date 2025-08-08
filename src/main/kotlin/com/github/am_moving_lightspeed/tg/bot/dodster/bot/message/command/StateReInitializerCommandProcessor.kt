package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

abstract class StateReInitializerCommandProcessor(
    private val stateManager: StateManager
): CommandProcessor() {

    override fun process(update: Update, api: DefaultAbsSender) {
        stateManager.dropState(update)
        stateManager.updateState(this, update, api)
    }
}
