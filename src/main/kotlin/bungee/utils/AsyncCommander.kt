package me.klop233.noir.bungee.utils

import me.dreamvoid.miraimc.api.bot.MiraiGroup
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.BaseComponent

class AsyncCommander(name: String, out: MiraiGroup): CommandSender {
    private var name: String
    private var messageChecker: Runnable

    init {
        this.name = name
        this.messageChecker = Runnable {
            checkMessage()
        }
    }

    private fun checkMessage() {

    }

    override fun getName(): String {
        return this.name
    }

    @Deprecated("Deprecated in Java", ReplaceWith("sendMessage(message: BaseComponent?)"))
    override fun sendMessage(message: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(vararg message: BaseComponent?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: BaseComponent?) {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java", ReplaceWith("sendMessage(vararg message: BaseComponent?)"))
    override fun sendMessages(vararg messages: String?) {
        TODO("Not yet implemented")
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