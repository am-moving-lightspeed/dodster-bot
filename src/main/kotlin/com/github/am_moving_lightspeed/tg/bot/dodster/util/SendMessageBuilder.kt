package com.github.am_moving_lightspeed.tg.bot.dodster.util

import org.telegram.telegrambots.meta.api.methods.ParseMode.MARKDOWNV2
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard

class SendMessageBuilder(
    private val chatId: Long,
    private val text: String
) {
    private var replyMarkup: ReplyKeyboard? = null

    fun replyMarkup(newReplyMarkup: ReplyKeyboard) = this.apply {
        replyMarkup = newReplyMarkup
    }

    fun build(): SendMessage = SendMessage.builder()
        .chatId(chatId)
        .parseMode(MARKDOWNV2)
        .replyMarkup(replyMarkup)
        .text(text)
        .build()
}
