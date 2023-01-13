package me.klop233.noir.bungee.storage

import java.sql.Connection
import java.sql.Statement

interface BaseSqlManager {
    var conn: Connection
    var stat: Statement

    fun setBind(name: String, id: Long)
    fun getBind(name: String): Long
    fun getBind(id: Long): String
}