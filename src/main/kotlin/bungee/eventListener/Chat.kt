package me.klop233.noir.bungee.eventListener

import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.api.bot.MiraiGroup
import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.event.GroupCommandType
import me.klop233.noir.bungee.event.NoirGroupCommandEvent
import me.klop233.noir.bungee.utils.MiraiUtil
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class Chat : Listener {
    @EventHandler
    fun onInGameChat(e: ChatEvent) {
        if (!e.message.startsWith(BungeeMain.getConfig().getString("command.chat.mc2qq.trigger")))
            return

        val bot: MiraiBot
        val group: MiraiGroup

        try {
            bot = MiraiBot.getBot(BungeeMain.getBotID())
            group = bot.getGroup(BungeeMain.getGroupID())
        } catch (e: NoSuchElementException) {
            BungeeMain.warn("The bot or group ID that you configured is not exists, check if your bot is logged")
            return
        }

        var message = BungeeMain.getConfig().getString("command.chat.mc2qq.format")
        message = message.replace("%name%", (e.sender as ProxiedPlayer).name)
        message = message.replace(
            "%message%",
            e.message.drop(BungeeMain.getConfig().getString("command.chat.mc2qq.trigger").length)
        )

        if (e.message
                .drop(BungeeMain.getConfig().getString("command.chat.mc2qq.trigger").length).isEmpty()
        ) // 如果信息为空不发送
            return

        MiraiUtil.sendMiraiMessageAsync(group, message)
    }

    @EventHandler
    fun onQQChat(e: NoirGroupCommandEvent) {
        if (e.getType() != GroupCommandType.CHAT)
            return

        var message = BungeeMain.getConfig().getString("command.chat.qq2mc.format")
        BungeeMain.info(message)
        val pureMessage = e.getEvent()
            .message.drop(BungeeMain.getConfig().getString("command.chat.qq2mc.trigger").length)

        message = ChatColor.translateAlternateColorCodes(
            '&', message
        )
        message = message.replace("%name%", e.getEvent().senderName)
        message = message.replace("%message%", pureMessage)

        BungeeMain.getInstance().proxy.broadcast(
            TextComponent(message)
        )
    }
}