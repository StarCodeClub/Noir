package me.klop233.noir.bungee.utils

import me.dreamvoid.miraimc.api.bot.MiraiGroup

class MiraiUtil {
    companion object {
        /**
         * Send a mirai message asynchronously
         * @param group a MiraiGroup instance
         * @param message message to send
         */
        fun sendMiraiMessageAsync(group: MiraiGroup, message: String) {
            ServerUtil.runAsyncTask {
                group.sendMessage(message)
            }
        }
    }
}