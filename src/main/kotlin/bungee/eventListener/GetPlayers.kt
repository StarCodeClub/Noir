package me.klop233.noir.bungee.eventListener

import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent
import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler
import java.lang.StringBuilder

class GetPlayers: Listener {
    @EventHandler
    fun onChat(e: NoirGroupCommandEvent) {
        if (e.getType() != GroupCommandType.GET_PLAYER)
            return

        val players = BungeeMain.getInstance().proxy.players
        var reply = BungeeMain.getConfig().getString("command.getPlayers.format")
        reply = reply.replace("%online%", players.size.toString())

        // 人数为零时发送人数为0直接结束
        if (players.isEmpty()) {
            // 只保留第一部分
            reply = reply.split("**")[0]
            MiraiUtil.sendMiraiMessageAsync(
                e.getEvent().group, reply
            )
            return
        }

        // 去掉 ** 分隔符
        reply = reply.replace("**", "")
        var playerList = StringBuilder()
        players.forEach { i ->
            playerList.append(i.name).append(", ")
        }
        playerList.removeSuffix(", ")
        reply = reply.replace("%players%", playerList.toString())

        MiraiUtil.sendMiraiMessageAsync(
            e.getEvent().group, reply
        )
    }
}