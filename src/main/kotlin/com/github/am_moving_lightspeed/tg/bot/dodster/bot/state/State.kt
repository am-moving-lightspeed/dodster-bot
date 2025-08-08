package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import java.util.Optional
import java.util.Properties
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * @see AbstractState
 */
interface State {

    val id: Id
    val previous: Optional<State>

    fun onStateEntered(update: Update, api: DefaultAbsSender)

    fun determineNextState(update: Update): Id

    fun onStateLeaving(update: Update, api: DefaultAbsSender) {
        // No-op by default
    }

    /**
     * @param value state identifier name.
     * @param type class representing a state corresponding to this ID.
     * @param options classes representing states that could be entered after transition from current state.
     */
    enum class Id(
        val value: String,
        val type: Class<out State>,
        vararg val options: Id
    ) {
        START("start", StartState::class.java),
        INITIAL("initial", InitialState::class.java, START)
    }

    class Context(val properties: Properties)
}
