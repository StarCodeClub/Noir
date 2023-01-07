package me.klop233.noir.bungee

import me.klop233.noir.BungeeMain
import me.klop233.noir.Messages
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Command
import java.util.*

class CommandHandler : Command("noir") {
    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (!sender.hasPermission("noir.access")) {
            sender.sendMessage(Messages.COMMAND_NO_PERMISSION.toComponent())
            return
        }

        if (args.isEmpty()) {
            sender.sendMessage(Messages.COMMAND_NO_ARGS.toComponent())
            return
        }

        when (args[0].lowercase(Locale.getDefault())) {
            "version" -> {
                versionCommandHandle(sender)
            }

            "reload" -> {
                // reloadCommandHandle(sender)
            }

            "help" -> {

            }

            "group" -> {
                // groupCommandHandle(sender, args)
            }
        }
    }

    private fun versionCommandHandle(sender: CommandSender) {
        val messages = mutableListOf<TextComponent>()
        fun add(msg: String) {
            messages.add(
                TextComponent(
                    ChatColor.translateAlternateColorCodes('&', msg)
                )
            )
        }

        add("")
        add("&dNoir information")
        add("&dNoir&7: &b${BungeeMain.version}")
        add("&aMiraiMC&7: &b${BungeeMain.miraiVersion}")
        add("&eBot ID&7： &b${BungeeMain.getBotID()}")
        add("&eGroup ID&7: &b${BungeeMain.getGroupID()}")
        add("&eDeveloped by Klop233. Please check github repo if you have any issue,you can also contact me through the information on my github profile,the github repo link is shown below")
        add("&ehttps://github.com/StarCraftOfficial/Noir")

        messages.forEach {
            sender.sendMessage(it)
        }
    }
}