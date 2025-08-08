package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.Id.INITIAL
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.Id.START
import java.util.Optional
import java.util.Optional.empty
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Technical state. Used as an “engine starter” to make it possible to start from START state.
 */
class InitialState(context: State.Context): AbstractState(context) {

    override val id: State.Id = INITIAL
    override val previous: Optional<State> = empty()

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        // No-op. Let it resolve in START state.
    }

    override fun determineNextState(update: Update): State.Id = START
}
