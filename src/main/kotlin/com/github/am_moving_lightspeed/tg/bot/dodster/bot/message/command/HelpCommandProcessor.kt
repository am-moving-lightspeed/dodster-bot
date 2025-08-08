package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.config.ApplicationProperties
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_CMD_RESPONSE_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD_NAME
import com.github.am_moving_lightspeed.tg.bot.dodster.util.SendMessageBuilder
import jakarta.inject.Inject
import java.util.Properties
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

class HelpCommandProcessor
//
@Inject
constructor(
    @ApplicationProperties properties: Properties
): CommandProcessor() {

    private val responseText: String = properties.getProperty("$BOT_CMD_RESPONSE_PREFIX.$HELP_CMD_NAME")

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        HELP_CMD == entity.text

    override fun process(update: Update, api: DefaultAbsSender) {
        SendMessageBuilder(update.message.chatId, responseText).apply {
            api.execute(build())
        }
    }
}
