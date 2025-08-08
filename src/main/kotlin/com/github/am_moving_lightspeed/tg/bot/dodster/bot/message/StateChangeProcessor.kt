package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.StateManager
import jakarta.inject.Inject
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class StateChangeProcessor
//
@Inject
constructor(private val stateManager: StateManager): NextProcessorAwareUpdateProcessor() {

    override fun canProcess(update: Update): Boolean {
        // TODO: clarify condition
        return true
    }

    override fun process(update: Update, api: DefaultAbsSender) {
        stateManager.updateState(this, update, api)
    }
}
