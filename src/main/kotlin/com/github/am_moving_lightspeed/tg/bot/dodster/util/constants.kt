package com.github.am_moving_lightspeed.tg.bot.dodster.util

// Bot commands
const val HELP_CMD = "/help"
const val SETTINGS_CMD = "/settings"
const val START_CMD = "/start"

val BOT_COMMANDS = listOf(
    HELP_CMD, SETTINGS_CMD, START_CMD
)

// Common
const val APPLICATION_PROPERTIES_FILE = "application.properties"

// Properties
const val BOT_PROP_PREFIX = "bot"
const val BOT_CMD_DESCRIPTION_PREFIX = "$BOT_PROP_PREFIX.command.description"

const val BOT_NAME_PROP = "$BOT_PROP_PREFIX.name"
const val BOT_TOKEN_PROP = "$BOT_PROP_PREFIX.token"

// Symbols
const val DASH = "-"
const val EQ = "="
const val SLASH = "/"
