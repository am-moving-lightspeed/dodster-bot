package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.Id.START
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_STATE_MESSAGE_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_STATE_OPTION_TPL
import com.github.am_moving_lightspeed.tg.bot.dodster.util.MEDIA_MGMT_OPT
import com.github.am_moving_lightspeed.tg.bot.dodster.util.MONITOR_CHAT_OPT
import com.github.am_moving_lightspeed.tg.bot.dodster.util.SendMessageBuilder
import java.util.Optional
import java.util.Optional.empty
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

/**
 * Entered upon initiating a dialog with the bot.
 */
class StartState(context: State.Context): AbstractState(context) {

    override val id: State.Id = START
    override val previous: Optional<State> = empty()

    private val stateMessage: String
        get() = context.properties.getProperty("$BOT_STATE_MESSAGE_PREFIX.${id.value}")

    private val mediaManagementOption: String
        get() = getStateOptionText(MEDIA_MGMT_OPT)

    private val monitorChatOption: String
        get() = getStateOptionText(MONITOR_CHAT_OPT)

    private val mediaManagementInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(mediaManagementOption, MEDIA_MGMT_OPT)

    private val monitorChatInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(monitorChatOption, MONITOR_CHAT_OPT)

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        val keyboard = InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(mediaManagementInlineKbBtn, monitorChatInlineKbBtn))
            .build()
        val reply = SendMessageBuilder(update.message.chatId, stateMessage)
            .replyMarkup(keyboard)
            .build()
        api.execute(reply)
    }

    override fun determineNextState(update: Update): State.Id {
        if (update.callbackQuery == null) {
            throw IllegalStateException("Callback query expected")
        }
        return update.callbackQuery.data.let { callbackData ->
            val foundOption = id.options.find { it.value == callbackData }
            foundOption ?: throw IllegalStateException("Callback data must match one of the options values")
        }
    }

    private fun getStateOptionText(option: String) =
        BOT_STATE_OPTION_TPL
            .format(id.value, option)
            .let { context.properties.getProperty(it) }

    private fun createInlineKbButton(text: String, callbackData: String): InlineKeyboardButton =
        InlineKeyboardButton
            .builder()
            .text(text)
            .callbackData(callbackData)
            .build()
}
