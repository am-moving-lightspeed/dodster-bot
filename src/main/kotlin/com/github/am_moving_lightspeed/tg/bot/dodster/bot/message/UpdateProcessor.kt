package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message

import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

interface UpdateProcessor {

    fun canProcess(update: Update): Boolean

    fun process(update: Update, api: DefaultAbsSender)
}
