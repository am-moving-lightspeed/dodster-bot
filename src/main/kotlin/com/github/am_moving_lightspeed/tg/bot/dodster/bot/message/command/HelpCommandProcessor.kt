package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.config.ApplicationProperties
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_CMD_RESPONSE_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD_NAME
import jakarta.inject.Inject
import java.util.Properties
import org.telegram.telegrambots.meta.api.objects.MessageEntity

class HelpCommandProcessor
//
@Inject
constructor(
    @ApplicationProperties properties: Properties
): PlainTextResponseCommandProcessor() {

    override val responseText: String = properties.getProperty("$BOT_CMD_RESPONSE_PREFIX.$HELP_CMD_NAME")

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        HELP_CMD == entity.text
}
