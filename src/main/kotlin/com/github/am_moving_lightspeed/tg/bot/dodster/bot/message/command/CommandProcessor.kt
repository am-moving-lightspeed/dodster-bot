package com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.command

import com.github.am_moving_lightspeed.tg.bot.dodster.bot.message.NextProcessorAwareUpdateProcessor
import java.util.Comparator.comparingInt
import org.telegram.telegrambots.meta.api.objects.EntityType.BOTCOMMAND
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

abstract class CommandProcessor: NextProcessorAwareUpdateProcessor() {

    override fun canProcess(update: Update): Boolean {
        val message = update.message
        return if (message == null || !message.isCommand) false
        else {
            val command = message.entities.stream()
                .filter { it.type == BOTCOMMAND }
                .sorted(comparingInt { it.offset })
                .findFirst()
                .orElseThrow()
            canProcessCommand(command)
        }
    }

    abstract fun canProcessCommand(entity: MessageEntity): Boolean
}
