package me.klop233.noir.bungee.storage

enum class SqlCommand(command: String) {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS")
    ;

    private var command: String

    init {
        this.command = command
    }

    override fun toString(): String {
        return command
    }
}