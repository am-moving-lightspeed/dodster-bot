package com.github.am_moving_lightspeed.tg.bot.dodster.bot.state

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId
import com.github.am_moving_lightspeed.tg.bot.dodster.bot.state.State.StateId.UPLOAD_MEDIA
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.meta.api.objects.Update

class UploadMediaState(override val previous: State): State {

    override val id: StateId = UPLOAD_MEDIA
    override val options: Map<StateId, State> = mapOf()

    override fun onStateEntered(update: Update, api: DefaultAbsSender) {
        TODO("Not yet implemented")
    }

    override fun determineNextState(update: Update): State {
        TODO("Not yet implemented")
    }
}
