package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.util.START_CMD
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

class StartCommandProcessor: CommandProcessor() {

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        entity.text == START_CMD

    override fun process(update: Update, api: DefaultAbsSender) {

    }
}
