package com.sogilis.survey

import java.sql.Connection

class ResponsesRepository(val conn: Connection) {
    fun saveNewOne() {
        conn.createStatement().use {
            it.execute("INSERT INTO sogisurvey.responses (author) VALUES ('test')")
        }
    }

    fun count(): Int = conn.createStatement().use {
        val rs = it.executeQuery("SELECT COUNT(*) FROM sogisurvey.responses")
        rs.next()
        rs.getInt(1)
    }
}