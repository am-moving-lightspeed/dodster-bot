package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

/**
 * It's mandatory for any State implementation to extend this class
 * as it declares the common constructor.
 */
abstract class AbstractState(protected val context: State.Context): State
