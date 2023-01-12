package me.klop233.noir.bungee.utils

import me.klop233.noir.BungeeMain
import net.md_5.bungee.api.scheduler.ScheduledTask

class ServerUtil {
    companion object {
        fun runAsyncTask(task: Runnable): ScheduledTask {
            return BungeeMain.getInstance().proxy.scheduler.runAsync(
                BungeeMain.getInstance(), task
            )
        }
    }
}