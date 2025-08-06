package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

abstract class PlainTextResponseCommandProcessor: CommandProcessor() {

    protected abstract val responseText: String

    override fun process(update: Update, api: DefaultAbsSender) {
        SendMessage.builder().apply {
            chatId(update.message.chatId)
            text(responseText)
            api.executeAsync(build())
        }
    }
}
