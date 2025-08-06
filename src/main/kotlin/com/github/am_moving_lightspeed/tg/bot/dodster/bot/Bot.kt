package com.github.am_moving_lightspeed.tg.bot.dodster.bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class Bot(
    private val botName: String,
    botToken: String
): TelegramLongPollingBot(botToken) {

    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update?) {

    }
}
