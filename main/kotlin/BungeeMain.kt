package me.klop233.noir

import me.klop233.noir.bungee.CommandHandler
import net.md_5.bungee.api.plugin.Plugin

class BungeeMain: Plugin() {
    override fun onEnable() {
        if (this.proxy.pluginManager.getPlugin("MiraiMC") == null) {
            warn(Messages.NO_MIRAIMC.toString())
            return
        }

        this.proxy.pluginManager.registerCommand(this, CommandHandler())

        info(Messages.WELCOME.toString())
    }

    override fun onDisable() {

    }

    fun info(message: String) {
        this.logger.info(message)
    }

    fun warn(message: String) {
        this.logger.warning(message)
    }
}

