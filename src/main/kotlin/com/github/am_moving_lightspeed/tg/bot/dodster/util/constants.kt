package com.github.am_moving_lightspeed.tg.bot.dodster.util

// Bot commands
const val HELP_CMD_NAME = "help"
const val SETTINGS_CMD_NAME = "settings"
const val START_CMD_NAME = "start"

const val HELP_CMD = "/$HELP_CMD_NAME"
const val SETTINGS_CMD = "/$SETTINGS_CMD_NAME"
const val START_CMD = "/$START_CMD_NAME"

val BOT_COMMANDS_NAMES = listOf(
    HELP_CMD_NAME, SETTINGS_CMD_NAME, START_CMD_NAME
)

// Common
const val APPLICATION_PROPERTIES_FILE = "application.properties"

// Properties
const val BOT_PROP_PREFIX = "bot"
const val BOT_CMD_DESCRIPTION_PREFIX = "$BOT_PROP_PREFIX.command.description"
const val BOT_CMD_RESPONSE_PREFIX = "$BOT_PROP_PREFIX.command.response"

const val BOT_NAME_PROP = "$BOT_PROP_PREFIX.name"
const val BOT_TOKEN_PROP = "$BOT_PROP_PREFIX.token"

// Symbols
const val DASH = "-"
const val EQ = "="
