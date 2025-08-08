package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.util.SendMessageBuilder
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

abstract class PlainTextResponseCommandProcessor: CommandProcessor() {

    protected abstract val responseText: String

    override fun process(update: Update, api: DefaultAbsSender) {
        SendMessageBuilder(update.message.chatId, responseText).apply {
            api.execute(build())
        }
    }
}
