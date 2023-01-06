package me.klop233.noir

import me.klop233.noir.bungee.CommandHandler
import me.klop233.noir.bungee.event.EventCaller
import me.klop233.noir.bungee.eventListener.Chat
import me.klop233.noir.bungee.eventListener.GetPlayers
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.nio.file.Files

class BungeeMain : Plugin() {
    override fun onEnable() {
        if (this.proxy.pluginManager.getPlugin("MiraiMC") == null) {
            warn(Messages.NO_MIRAIMC.toString())
            return
        }
        instance = this

        info("Dependency check pass, loading Noir v$version")

        // 读取配置文件
        saveDataFolder()
        saveDefaultConfig()
        config = ConfigurationProvider.getProvider(YamlConfiguration::class.java)
            .load(File(this.dataFolder, "config.yml"))

        // 初始化变量
        miraiVersion = this.proxy.pluginManager.getPlugin("MiraiMC").description.version
        version = this.description.version
        botID = config.getLong("general.botID")
        groupID = config.getLong("general.groupID")
        admin = config.getLongList("general.admin")

        // 注册命令和事件
        this.proxy.pluginManager.registerCommand(this, CommandHandler())
        this.proxy.pluginManager.registerListener(this, EventCaller())
        this.proxy.pluginManager.registerListener(this, GetPlayers())
        this.proxy.pluginManager.registerListener(this, Chat())

        info(Messages.WELCOME.toString())
        info("Environment: Noir $version   MiraiMC $miraiVersion")
    }

    override fun onDisable() {
        Messages.GOODBYE.toString()
    }


    private fun saveDataFolder() {
        if (!this.dataFolder.exists())
            this.dataFolder.mkdir()
    }

    private fun saveDefaultConfig() {
        val config = File(this.dataFolder, "config.yml")

        if (!config.exists()) {
            Files.copy(this.getResourceAsStream("config.yml"), config.toPath())
        }
    }

    companion object {
        private lateinit var instance: BungeeMain
        private var config = Configuration()
        private var miraiVersion = ""
        private var version = ""
        private var botID = 0L
        private var groupID = 0L
        private var admin = listOf<Long>()

        fun info(message: String) {
            instance.logger.info(message)
        }

        fun warn(message: String) {
            instance.logger.warning(message)
        }

        fun getConfig(): Configuration {
            return config
        }

        fun getBotID(): Long {
            return botID
        }

        fun getGroupID(): Long {
            return groupID
        }

        fun getInstance(): BungeeMain {
            return instance
        }
    }
}

