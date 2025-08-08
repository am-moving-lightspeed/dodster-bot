package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.Id.SETTINGS
import com.github.am_moving_lightspeed.tg.bot.dodster.util.ADD_ACCOUNT_OPT
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_STATE_MESSAGE_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_OPT
import com.github.am_moving_lightspeed.tg.bot.dodster.util.REMOVE_OPT
import com.github.am_moving_lightspeed.tg.bot.dodster.util.RE_AUTH_OPT
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
class SettingsState(context: State.Context): AbstractState(context) {

    override val id: State.Id = SETTINGS
    override val previous: Optional<State> = empty()

    private val settingsMessage: String
        get() = context.properties.getProperty("$BOT_STATE_MESSAGE_PREFIX.${id.value}")

    private val addAccountOption: String
        get() = getStateOptionText(ADD_ACCOUNT_OPT)

    private val helpOption: String
        get() = getStateOptionText(HELP_OPT)

    private val removeOption: String
        get() = getStateOptionText(REMOVE_OPT)

    private val reAuthOption: String
        get() = getStateOptionText(RE_AUTH_OPT)

    private val addAccountInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(addAccountOption, ADD_ACCOUNT_OPT)

    private val helpInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(helpOption, HELP_OPT)

    private val removeInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(removeOption, REMOVE_OPT)

    private val reAuthInlineKbBtn: InlineKeyboardButton
        get() = createInlineKbButton(reAuthOption, RE_AUTH_OPT)

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        val firstKbRow = listOf(addAccountInlineKbBtn, removeInlineKbBtn, helpInlineKbBtn)
        // TODO list accounts one row per each that require re-authorization
        // TODO check for "Removal" relevance
        val keyboard = InlineKeyboardMarkup.builder()
            .keyboardRow(firstKbRow)
            .build()
        val reply = SendMessageBuilder(update.message.chatId, settingsMessage)
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

    private fun createInlineKbButton(text: String, callbackData: String): InlineKeyboardButton =
        InlineKeyboardButton
            .builder()
            .text(text)
            .callbackData(callbackData)
            .build()
}
