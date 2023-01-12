package me.klop233.noir.bungee.eventListener

import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent
import me.klop233.noir.bungee.utils.AsyncCommander
import me.klop233.noir.bungee.utils.Commander
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.scheduler.ScheduledTask
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

        val commander = AsyncCommander(
            BungeeMain.getConfig().getString("command.execute.sender-name"),
            e.getEvent().group,
            command
        )

        try {
            // commander 实例化后自己会检查收到的信息并发送到群里
            BungeeMain.getInstance().proxy.pluginManager.dispatchCommand(commander, command)
        } catch (e: Exception) {
            e.printStackTrace()
            BungeeMain.warn("An error was occurred while Noir executing a command, check the message above")
            return
        }
        MiraiUtil.sendMiraiMessageAsync(e.getEvent().group,
            "Task sent, task ID: ${commander.getTask().id}")
    }

    companion object {
        // 命令任务池
        var taskPool = mutableListOf<AsyncCommander>()
    }
}