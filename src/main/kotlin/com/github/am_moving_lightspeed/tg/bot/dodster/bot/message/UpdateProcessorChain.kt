package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message

import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

interface UpdateProcessorChain {

    var next: UpdateProcessorChain?

    fun canProcess(update: Update): Boolean

    fun process(update: Update, api: DefaultAbsSender)

    fun processInChain(update: Update, api: DefaultAbsSender) {
        if (canProcess(update)) {
            process(update, api)
            return
        }
        next?.processInChain(update, api)
    }
}
