package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.INITIAL
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.START
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class InitialState: State {

    override val id: StateId = INITIAL
    override val previous: State? = null
    override val options: Map<StateId, State> = mapOf(
        START to StartState()
    )

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        // No-op. Let it resolve in START state.
    }

    override fun determineNextState(update: Update): State = options[START]!!
}
