package me.klop233.noir.bungee.utils

import me.dreamvoid.miraimc.api.bot.MiraiGroup

class MiraiUtil {
    companion object {
        fun sendMiraiMessageAsync(group: MiraiGroup, message: String) {
            ServerUtil.runAsyncTask {
                group.sendMessage(message)
            }
        }
    }
}