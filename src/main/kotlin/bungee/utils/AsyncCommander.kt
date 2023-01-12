package me.klop233.noir.bungee.utils

import me.dreamvoid.miraimc.api.bot.MiraiGroup
import me.klop233.noir.BungeeMain
import me.klop233.noir.bungee.eventListener.CommandDispatch
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.scheduler.ScheduledTask


class AsyncCommander(name: String, out: MiraiGroup, command: String): CommandSender {
    private var name: String
    private var message = mutableListOf<String>()
    private var messageChecker: Runnable
    private var group: MiraiGroup
    private var task: ScheduledTask
    private var command: String

    init {
        this.name = name
        this.command = command
        this.messageChecker = Runnable {
            checkMessage()
        }
        this.group = out
        this.task = ServerUtil.runAsyncTask(messageChecker)
        CommandDispatch.taskPool.add(this)
    }

    private fun checkMessage() {
        // under async
        val sb = StringBuilder()
        while (true) {
            Thread.sleep(1000)
            if (message.isEmpty())
                continue
            this.message.forEach {
                sb.append(it).append("\n")
            }
            MiraiUtil.sendMiraiMessageAsync(this.group, sb.toString())
            sb.clear()
            message.clear()
        }
    }

    fun getTask(): ScheduledTask {
        return this.task
    }

    fun getCommand(): String {
        return this.command
    }

    override fun getName(): String {
        return this.name
    }

    @Deprecated("Deprecated in Java", ReplaceWith("sendMessage(message: BaseComponent?)"))
    override fun sendMessage(message: String) {
        this.message.add(ChatColor.stripColor(message))
    }

    override fun sendMessage(vararg message: BaseComponent) {
        message.forEach {
            this.message.add(ChatColor.stripColor(it.toLegacyText()))
        }
    }

    override fun sendMessage(message: BaseComponent) {
        this.message.add(ChatColor.stripColor(message.toLegacyText()))
    }

    @Deprecated("Deprecated in Java", ReplaceWith("sendMessage(vararg message: BaseComponent?)"))
    override fun sendMessages(vararg messages: String) {
        messages.forEach {
            this.message.add(ChatColor.stripColor(it))
        }
    }

    override fun getGroups(): MutableCollection<String>? {
        return null
    }

    override fun addGroups(vararg groups: String?) {

    }

    override fun removeGroups(vararg groups: String?) {

    }

    override fun hasPermission(permission: String?): Boolean {
        return true
    }

    override fun setPermission(permission: String?, value: Boolean) {

    }

    override fun getPermissions(): MutableCollection<String> {
        return mutableListOf<String>("*")
    }
}