package me.klop233.noir.bungee.utils

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.BaseComponent

class Commander(senderName: String): CommandSender {
    private var name: String
    private var messages = mutableListOf<String>()

    init {
        name = senderName
    }

    fun getMessage(): MutableList<String> {
        return this.messages
    }

    override fun getName(): String {
        return this.name
    }

    @Deprecated("Deprecated in Bungeecord API")
    override fun sendMessage(message: String) {
        this.messages.add(message)
    }

    override fun sendMessage(vararg message: BaseComponent) {
        message.forEach {
            this.messages.add(it.toLegacyText())
        }
    }

    override fun sendMessage(message: BaseComponent) {
        this.messages.add(message.toLegacyText())
    }

    @Deprecated("Deprecated in Bungeecord API")
    override fun sendMessages(vararg messages: String) {
        this.messages.addAll(messages)
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

    override fun getPermissions(): MutableCollection<String>? {
        return null
    }
}