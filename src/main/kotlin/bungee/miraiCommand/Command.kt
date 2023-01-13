package me.klop233.noir.bungee.miraiCommand

import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent

interface Command {
    var event: NoirGroupCommandEvent
    var type: GroupCommandType
}