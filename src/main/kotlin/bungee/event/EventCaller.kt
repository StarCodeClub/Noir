package me.klop233.noir.bungee.event

import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent
import me.klop233.noir.BungeeMain
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class EventCaller : Listener {
    @EventHandler
    fun onMiraiGroupMessage(e: MiraiGroupMessageEvent) {
        if (e.botID != BungeeMain.getConfig().getLong("general.botID"))
            return
        if (e.groupID != BungeeMain.getConfig().getLong("general.groupID"))
            return

        val message = e.message

        if (message.startsWith(BungeeMain.getConfig().getString("command.getPlayers.trigger")))
            call(GroupCommandType.GET_PLAYER, e)
        if (message.startsWith(BungeeMain.getConfig().getString("command.chat.qq2mc.trigger")))
            call(GroupCommandType.CHAT, e)
    }

    private fun call(type: GroupCommandType, e: MiraiGroupMessageEvent) {
        BungeeMain.getInstance().proxy.pluginManager.callEvent(
            NoirGroupCommandEvent(
                type, e
            )
        )
    }
}