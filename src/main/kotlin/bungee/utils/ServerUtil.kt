package me.klop233.noir.bungee.utils

import me.klop233.noir.BungeeMain

class ServerUtil {
    companion object {
        fun runAsyncTask(task: Runnable) {
            BungeeMain.getInstance().proxy.scheduler.runAsync(
                BungeeMain.getInstance(), task
            )
        }
    }
}