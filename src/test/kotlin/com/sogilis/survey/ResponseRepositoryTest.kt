package com.sogilis.survey

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension


class ResponseRepositoryTest {

    companion object {
        @JvmField
        @RegisterExtension
        var database = DbExtension()
    }

    @Test
    fun saveNewOne() {
        ResponsesRepository(database.connection).saveNewOne()
        database.connection.createStatement().use {
            val rs = it.executeQuery("SELECT COUNT(*) FROM responses")
            rs.next()
            val count = rs.getInt(1)
            Assertions.assertEquals(1, count)
        }
    }

}