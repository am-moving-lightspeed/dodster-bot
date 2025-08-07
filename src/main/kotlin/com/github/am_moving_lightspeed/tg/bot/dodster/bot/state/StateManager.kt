package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.util.letOrThrow
import com.github.am_moving_lightspeed.tg.bot.dodster.util.runOrThrow
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class StateManager {

    private val usersStates: MutableMap<Long, State> = HashMap()

    fun dropState(update: Update) {
        usersStates.remove(update.message.chatId)
    }

    fun updateState(update: Update, api: DefaultAbsSender) {
        val chatId = update.message.chatId
        if (!usersStates.containsKey(chatId)) {
            initUserState(chatId)
        }
        usersStates[chatId].letOrThrow {
            determineNextState(update, chatId, it)
            it.onStateLeaving(update, api)
        }
        usersStates[chatId].runOrThrow {
            onStateEntered(update, api)
        }
    }

    private fun initUserState(chatId: Long) {
        usersStates[chatId] = InitialState()
    }

    private fun determineNextState(update: Update, chatId: Long, currentState: State) {
        currentState.determineNextState(update).let {
            usersStates[chatId] = it
        }
    }
}
