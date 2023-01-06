package me.klop233.noir

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent

enum class Messages(message: String) {
    WELCOME("欢迎使用 Noir, 插件已加载完毕"),
    GOODBYE("Noir 已卸载"),
    COMMAND_NO_PERMISSION("&dNoir &a已经成功加载, &c但是你没有权限使用 &5Noir"),
    COMMAND_NO_ARGS("&dNoir &a已经成功加载, 使用 &e/noir help &a来查看帮助"),
    NO_MIRAIMC("你没有安装 MiraiMC, 请先安装 MiraiMC --> https://github.com/DreamVoid/MiraiMC")
    ;

    private var message = ""

    init {
        this.message = message
    }

    override fun toString(): String {
        return ChatColor.translateAlternateColorCodes(
            '&', message
        )
    }

    fun toComponent(): TextComponent {
        return TextComponent(
            ChatColor.translateAlternateColorCodes(
                '&', message
            )
        )
    }
}