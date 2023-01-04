package me.klop233.noir.bungee.eventListener

import me.dreamvoid.miraimc.bungee.event.message.passive.MiraiGroupMessageEvent
import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler
import java.lang.StringBuilder

class GetPlayers: Listener {
    @EventHandler
    fun onGroupGetPlayers(e: MiraiGroupMessageEvent) {
        if (!e.message.startsWith(BungeeMain.getConfig()
            .getString("command.getPlayers.trigger")))
            return
        if (e.botID != BungeeMain.getBotID())
            return
        if (e.groupID != BungeeMain.getGroupID())
            return

        val players = BungeeMain.getInstance().proxy.players
        var reply = BungeeMain.getConfig().getString("command.getPlayers.format")
        reply = reply.replace("%online%", players.size.toString())

        // 人数为零时发送人数为0直接结束
        if (players.isEmpty()) {
            // 只保留第一部分
            reply = reply.split("**")[0]
            MiraiUtil.sendMiraiMessageAsync(
                e.group, reply
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
            e.group, reply
        )
    }
}