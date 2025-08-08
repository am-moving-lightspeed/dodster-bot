package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.Id.INITIAL
import java.util.Optional
import java.util.Optional.empty
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Technical state. Used as an “engine starter”.
 */
class InitialState(
    private val next: State.Id,
    context: State.Context
): AbstractState(context) {

    override val id: State.Id = INITIAL
    override val previous: Optional<State> = empty()

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        // No-op. Let it resolve into `next` state.
    }

    override fun determineNextState(update: Update): State.Id = next
}
