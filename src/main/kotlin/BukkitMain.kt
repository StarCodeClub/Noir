package me.klop233.noir

import org.bukkit.plugin.java.JavaPlugin

class BukkitMain : JavaPlugin() {
    override fun onEnable() {
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