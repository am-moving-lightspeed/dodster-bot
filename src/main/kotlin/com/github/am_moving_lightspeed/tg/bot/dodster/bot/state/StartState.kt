package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.ADD_STORAGE
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.START
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.UPLOAD_MEDIA
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Entered upon initiating a dialog with the bot.
 */
class StartState: State {

    override val id: StateId = START
    override val previous: State? = null
    override val options: Map<StateId, State> = mapOf(
        ADD_STORAGE to AddStorageState(this),
        UPLOAD_MEDIA to UploadMediaState(this)
    )

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {

    }

    override fun determineNextState(update: Update): State {
        TODO("Not yet implemented")
    }
}
