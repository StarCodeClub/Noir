package me.klop233.noir.bungee.miraiCommand

import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent

class FriendCommand: Command {
    override lateinit var event: NoirGroupCommandEvent
    override lateinit var type: GroupCommandType
}