package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message

abstract class NextProcessorAwareUpdateProcessor: UpdateProcessorChain {

    override var next: UpdateProcessorChain? = null
}
