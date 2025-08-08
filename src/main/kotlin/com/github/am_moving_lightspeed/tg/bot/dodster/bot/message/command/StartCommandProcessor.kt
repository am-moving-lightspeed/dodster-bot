package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import com.github.am_moving_lightspeed.tg.bot.dodster.util.START_CMD
import jakarta.inject.Inject
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

class StartCommandProcessor
//
@Inject
constructor(private val stateManager: StateManager): CommandProcessor() {

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        START_CMD == entity.text

    override fun process(update: Update, api: DefaultAbsSender) {
        stateManager.dropState(update)
        stateManager.updateState(update, api)
    }
}
