package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.ADD_STORAGE
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class AddStorageState(override val previous: State): State {

    override val id: StateId = ADD_STORAGE
    override val options: Map<StateId, State> = mapOf()

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        TODO("Not yet implemented")
    }

    override fun determineNextState(update: Update): State {
        TODO("Not yet implemented")
    }
}
