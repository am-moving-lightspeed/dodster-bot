package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.util.letOrThrow
import com.github.am_moving_lightspeed.tg.bot.dodster.util.runOrThrow
import java.util.Properties
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class StateManager(properties: Properties) {

    private val context: State.Context = State.Context(properties)
    private val usersStates: MutableMap<Long, State> = HashMap()

    fun dropState(update: Update) {
        usersStates.remove(update.message.chatId)
    }

    fun updateState(update: Update, api: DefaultAbsSender) {
        val chatId = resolveChatId(update)
        if (!usersStates.containsKey(chatId)) {
            initUserState(chatId)
        }
        usersStates[chatId].letOrThrow {
            determineNextState(update, chatId, it)
        }
        usersStates[chatId].runOrThrow {
            onStateEntered(update, api)
        }
    }

    private fun resolveChatId(update: Update): Long {
        return when {
            update.message != null -> update.message.chatId
            update.callbackQuery?.message != null -> update.callbackQuery.message.chatId
            else -> throw IllegalStateException("Could not figure out the ChatId")
        }
    }

    private fun initUserState(chatId: Long) {
        usersStates[chatId] = InitialState(context)
    }

    private fun determineNextState(update: Update, chatId: Long, currentState: State) {
        currentState.determineNextState(update).let {
            usersStates[chatId] = initNextState(it)
        }
    }

    private fun initNextState(stateId: State.Id): State {
        return stateId.type
            .getConstructor(State.Context::class.java)
            .newInstance(context)
    }
}
