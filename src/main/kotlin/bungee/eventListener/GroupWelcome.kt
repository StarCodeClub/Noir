package me.klop233.noir.bungee.eventListener

import me.dreamvoid.miraimc.bungee.event.group.member.MiraiMemberJoinEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class GroupWelcome: Listener {
    @EventHandler
    fun onJoin(e: MiraiMemberJoinEvent) {
        e.group.sendMessage("")
    }
}