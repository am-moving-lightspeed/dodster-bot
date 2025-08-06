package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_CMD_RESPONSE_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD
import com.github.am_moving_lightspeed.tg.bot.dodster.util.HELP_CMD_NAME
import jakarta.inject.Inject
import jakarta.inject.Named
import org.telegram.telegrambots.meta.api.objects.MessageEntity

class HelpCommandProcessor
@Inject
constructor(
    @Named("$BOT_CMD_RESPONSE_PREFIX.$HELP_CMD_NAME") override val responseText: String
): PlainTextResponseCommandProcessor() {

    override fun canProcessCommand(entity: MessageEntity): Boolean =
        HELP_CMD == entity.text
}
