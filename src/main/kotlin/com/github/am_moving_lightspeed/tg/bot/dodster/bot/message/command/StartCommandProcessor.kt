package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import com.github.am_moving_lightspeed.tg.bot.dodster.util.START_CMD
import jakarta.inject.Inject
import org.telegram.telegrambots.meta.api.objects.MessageEntity

class StartCommandProcessor
//
@Inject
constructor(stateManager: StateManager): StateReInitializerCommandProcessor(stateManager) {

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        START_CMD == entity.text
}
