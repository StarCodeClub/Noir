package me.klop233.noir.bungee.storage

import java.sql.Connection
import java.sql.Statement

class MySqlManager: BaseSqlManager {
    override var conn: Connection
        get() = TODO("Not yet implemented")
        set(value) {}
    override var stat: Statement
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun setBind(name: String, id: Long) {
        TODO("Not yet implemented")
    }

    override fun getBind(name: String): Long {
        TODO("Not yet implemented")
    }

    override fun getBind(id: Long): String {
        TODO("Not yet implemented")
    }
}