package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_CMD_DESCRIPTION_PREFIX
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_COMMANDS
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_NAME_PROP
import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_TOKEN_PROP
import com.github.am_moving_lightspeed.tg.bot.dodster.util.removeCommandPrefix
import java.util.Properties
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand

class Bot(
    private val properties: Properties
): TelegramLongPollingBot(
    properties.getProperty(BOT_TOKEN_PROP)
) {

    override fun getBotUsername(): String = properties.getProperty(BOT_NAME_PROP)

    override fun onUpdateReceived(update: Update?) {

    }

    override fun onRegister() {
        updateCommandsList()
    }

    private fun updateCommandsList() = SetMyCommands.builder().apply {
        BOT_COMMANDS.forEach { command ->
            val description = "$BOT_CMD_DESCRIPTION_PREFIX.${command.removeCommandPrefix()}"
                .let { properties.getProperty(it) }
            command(BotCommand(command, description))
        }
        execute(build())
    }
}
