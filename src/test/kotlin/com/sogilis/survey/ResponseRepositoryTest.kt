package com.sogilis.survey

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension


class ResponseRepositoryTest {

    companion object {
        @JvmField
        @RegisterExtension
        var database = DbExtension()
    }

    @BeforeEach
    fun setupDb() = setupDatabase(database.connection)

    @Test
    fun saveNewOne() {
        ResponsesRepository(database.connection).saveNewOne("userName")
        database.connection.createStatement().use {
            val rs = it.executeQuery("SELECT COUNT(*) FROM sogisurvey.responses")
            rs.next()
            val count = rs.getInt(1)
            Assertions.assertEquals(1, count)
        }
    }

}