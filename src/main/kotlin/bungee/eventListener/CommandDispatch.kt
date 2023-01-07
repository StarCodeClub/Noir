package me.klop233.noir.bungee.eventListener

import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent
import me.klop233.noir.bungee.utils.Commander
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler


class CommandDispatch: Listener {
    @EventHandler
    fun onCommandDispatch(e: NoirGroupCommandEvent) {
        if (e.getType() != GroupCommandType.EXECUTE_COMMAND)
            return
        if (!BungeeMain.getAdmin().contains(e.getEvent().senderID))
            return

        val command = e.getEvent().message
            .drop(BungeeMain.getConfig().getString("command.execute.trigger").length)

        val commander = Commander(
            BungeeMain.getConfig().getString("command.execute.sender-name")
        )

        try {
            BungeeMain.getInstance().proxy.pluginManager.dispatchCommand(commander, command)
        } catch (e: Exception) {
            e.printStackTrace()
            BungeeMain.warn("An error was occurred while Noir executing a command, check the message above")
            return
        }


        if (commander.getMessage().isEmpty()) {
            MiraiUtil.sendMiraiMessageAsync(e.getEvent().group, "命令无返回")
            return
        }

        val reply = StringBuilder()
        commander.getMessage().forEach {
            reply.append(it).append("\n")
        }

        MiraiUtil.sendMiraiMessageAsync(
            e.getEvent().group, ChatColor.stripColor(reply.toString())
        )
    }
}