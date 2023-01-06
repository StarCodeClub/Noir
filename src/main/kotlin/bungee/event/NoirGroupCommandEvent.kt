package me.klop233.noir.bungee.event

import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent
import net.md_5.bungee.api.plugin.Event

class NoirGroupCommandEvent(type: GroupCommandType, miraiEvent: MiraiGroupMessageEvent) : Event() {
    init {
        type.also { this.type = it }
        miraiEvent.also { this.miraiEvent = it }
    }

    private var type: GroupCommandType
    private var miraiEvent: MiraiGroupMessageEvent

    fun getType(): GroupCommandType {
        return type
    }

    fun getEvent(): MiraiGroupMessageEvent {
        return miraiEvent
    }


}