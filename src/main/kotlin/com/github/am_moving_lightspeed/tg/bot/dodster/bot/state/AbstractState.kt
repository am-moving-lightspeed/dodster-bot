package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.util.BOT_STATE_OPTION_TPL

/**
 * It's mandatory for any State implementation to extend this class
 * as it declares the common constructor.
 */
abstract class AbstractState(protected val context: State.Context): State {

    protected fun getStateOptionText(option: String): String =
        BOT_STATE_OPTION_TPL
            .format(id.value, option)
            .let { context.properties.getProperty(it) }
}
