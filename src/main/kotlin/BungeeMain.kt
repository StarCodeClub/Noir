package me.klop233.noir

import me.klop233.noir.bungee.CommandHandler
import me.klop233.noir.bungee.eventListener.GetPlayers
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.nio.file.Files

abstract class BungeeMain: Plugin() {
    private var miraiVersion = ""
    private var version = ""
    private var group = 0L
    private var bot = 0L
    private lateinit var config: Configuration

    override fun onEnable() {
        if (this.proxy.pluginManager.getPlugin("MiraiMC") == null) {
            warn(Messages.NO_MIRAIMC.toString())
            return
        }

        // 读取配置文件
        saveDataFolder()
        saveDefaultConfig()
        config = ConfigurationProvider.getProvider(YamlConfiguration::class.java)
            .load(File(this.dataFolder, "config.yml"))

        // 初始化变量
        miraiVersion = this.proxy.pluginManager.getPlugin("MiraiMC").description.version
        version = this.description.version
        group = config.getLong("general.groupID")
        bot = config.getLong("general.botID")

        // 注册命令和事件
        this.proxy.pluginManager.registerCommand(this, CommandHandler())
        this.proxy.pluginManager.registerListener(this, GetPlayers())

        info(Messages.WELCOME.toString())
    }

    override fun onDisable() {

    }

    private fun info(message: String) {
        this.logger.info(message)
    }

    private fun warn(message: String) {
        this.logger.warning(message)
    }

    private fun saveDataFolder() {
        if (!this.dataFolder.exists())
            this.dataFolder.mkdir()
    }

    fun saveDefaultConfig() {
        var config = File(this.dataFolder, "config.yml")

        if (!config.exists()) {
            Files.copy(this.getResourceAsStream("config.yml"), file.toPath())
        }
    }
}

