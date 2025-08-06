package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.UpdateProcessor
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_CMD_DESCRIPTION_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_COMMANDS_NAMES
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_NAME_PROP
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_TOKEN_PROP
import java.util.Properties
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand

class Bot(
    private val processors: Set<UpdateProcessor>,
    private val properties: Properties
): TelegramLongPollingBot(
    properties.getProperty(BOT_TOKEN_PROP)
) {

    override fun getBotUsername(): String = properties.getProperty(BOT_NAME_PROP)

    override fun onUpdateReceived(update: Update?) {
        if (update != null) {
            processors
                .find { it.canProcess(update) }
                ?.process(update, this)
        }
    }

    override fun onRegister() {
        updateCommandsList()
    }

    private fun updateCommandsList() = SetMyCommands.builder().apply {
        BOT_COMMANDS_NAMES.forEach { command ->
            val description = "$BOT_CMD_DESCRIPTION_PREFIX.$command"
                .let { properties.getProperty(it) }
            command(BotCommand(command, description))
        }
        execute(build())
    }
}
