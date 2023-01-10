package me.klop233.noir.bungee.eventListener

import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class GetPlayers : Listener {
    @EventHandler
    fun onChat(e: NoirGroupCommandEvent) {
        if (e.getType() != GroupCommandType.GET_PLAYER)
            return

        val players = BungeeMain.getInstance().proxy.players
        val servers = BungeeMain.getInstance().proxy.servers
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
        val playerList = StringBuilder()
        var serverNick: String
        servers.values.forEach {
            // 如果有服务器nick则设置，否则保持原来名称
            serverNick = BungeeMain.getConfig().getString("command.getPlayers.server-nick." + it.name)
            serverNick = if (serverNick != "")
                serverNick
            else
                it.name

            if (!it.players.isEmpty()) {
                playerList.append("\n")
                playerList.append("[").append(serverNick).append(" ").append(it.players.size).append("]").append(" ")
                it.players.forEach { player ->
                    playerList.append(player.name).append(", ")
                }
                playerList.deleteCharAt(playerList.length - 1)
                    .deleteCharAt(playerList.length - 1)// 删除最后一个 ", "
            }
        }
        reply = reply.replace("%players%", playerList.toString())
        MiraiUtil.sendMiraiMessageAsync(
            e.getEvent().group, reply
        )
    }
}