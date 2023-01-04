package me.klop233.noir.bungee

import me.klop233.noir.Messages
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command

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
    }
}