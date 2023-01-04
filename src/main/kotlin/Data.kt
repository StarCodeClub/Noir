package me.klop233.noir

class Data(botID: Long,
    groupID: Long,
    admin: List<Long>) {
    private var botID = 0L
    private var groupID = 0L
    private var admin = listOf<Long>()


    init {
        this.botID = botID
        this.groupID = groupID
        this.admin = admin
    }
}