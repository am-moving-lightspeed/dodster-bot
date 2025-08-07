package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

interface State {

    val id: StateId
    val previous: State?
    val options: Map<StateId, State>

    fun onStateEntered(update: Update, api: DefaultAbsSender)

    fun determineNextState(update: Update): State

    fun onStateLeaving(update: Update, api: DefaultAbsSender) {
        // No-op by default
    }

    enum class StateId {
        ADD_STORAGE,
        INITIAL,  // Technical state, which resolves into START soon after entered.
        START,
        UPLOAD_MEDIA
    }
}
