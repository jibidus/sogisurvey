package com.sogilis.survey

import java.sql.Connection

class ResponsesRepository(val conn: Connection) {
    fun saveNewOne() {
        conn.createStatement().use {
            it.execute("INSERT INTO responses (author) VALUES ('test')")
        }
    }
}