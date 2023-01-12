package me.klop233.noir.bungee.eventListener

import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class FuckQiu: Listener {
    @EventHandler
    fun onQiuShit(e: MiraiGroupMessageEvent) {
        val msg = e.message
        if (msg.contains("...") ||
            msg.contains("…") ||
            msg.matches(Regex("《(.*?)》"))) {
            e.recall()
            e.reply("傻逼")
        }
    }
}