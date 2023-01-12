package me.klop233.noir.bungee

import me.klop233.noir.BungeeMain
import me.klop233.noir.Messages
import me.klop233.noir.bungee.eventListener.CommandDispatch
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.util.*
import javax.xml.soap.Text

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
                reloadCommandHandle(sender)
            }

            "help" -> {
                helpCommandHandle(sender)
            }

            "group" -> {
                // groupCommandHandle(sender, args)
            }

            "tasks" -> {
                tasksCommandHandle(sender)
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

        add("&dNoir information")
        add("&dNoir&7: &b${BungeeMain.version}")
        add("&aMiraiMC&7: &b${BungeeMain.miraiVersion}")
        add("&eBot ID&7： &b${BungeeMain.getBotID()}")
        add("&eGroup ID&7: &b${BungeeMain.getGroupID()}")
        add("&eDeveloped by Klop233")

        messages.forEach {
            sender.sendMessage(it)
        }
    }

    private fun reloadCommandHandle(sender: CommandSender) {
        BungeeMain.config = ConfigurationProvider.getProvider(YamlConfiguration::class.java)
            .load(File(BungeeMain.getInstance().dataFolder, "config.yml"))
        BungeeMain.loadConfig()
        sender.sendMessage(TextComponent(ChatColor.LIGHT_PURPLE.toString() +  "Noir config reload successfully"))
    }

    private fun helpCommandHandle(sender: CommandSender) {
        val messages = mutableListOf<TextComponent>()
        fun add(msg: String) {
            messages.add(
                TextComponent(
                    ChatColor.translateAlternateColorCodes('&', msg)
                )
            )
        }

        add("&dNoir version    &e-- show the information of Noir")
        add("&dNoir reload     &e-- reload config")
        add("&dNoir help       &e-- show this help")
        add("&dNoir group      &e-- show group command help")
        add("&dNoir tasks      &e-- show command dispatch tasks")

        messages.forEach {
            sender.sendMessage(it)
        }
    }

    private fun tasksCommandHandle(sender: CommandSender) {
        sender.sendMessage(
            "${ChatColor.LIGHT_PURPLE}Noir Total tasks: ${CommandDispatch.taskPool.size}"
        )
        CommandDispatch.taskPool.forEach {
            sender.sendMessage("${ChatColor.YELLOW} ID: ${it.getTask().id} Command: ${it.getCommand()} Sender: ${it.name}")
        }
    }
}