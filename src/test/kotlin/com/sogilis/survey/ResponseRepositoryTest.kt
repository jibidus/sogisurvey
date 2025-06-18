package com.sogilis.survey

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.testcontainers.containers.PostgreSQLContainer
import java.sql.Connection
import java.sql.DriverManager

class ResponseRepositoryTest {

    companion object{
        private lateinit var postgresContainer: PostgreSQLContainer<*>
        private lateinit var connection: Connection
        private lateinit var repository: ResponsesRepository

        @BeforeAll
        @JvmStatic
        fun setup() {
            postgresContainer = PostgreSQLContainer<Nothing>("postgres:16-alpine")
            postgresContainer.start()

            connection = DriverManager.getConnection(
                postgresContainer.jdbcUrl,
                postgresContainer.username,
                postgresContainer.password
            )

            connection.createStatement().execute(
                """
                CREATE TABLE responses (
                    id SERIAL PRIMARY KEY,
                    author VARCHAR(255) NOT NULL
                )
                """
            )

            repository = ResponsesRepository(connection)
        }

        @AfterAll
        @JvmStatic
        fun teardown() {
            connection.close()
            postgresContainer.stop()
        }
    }

    @Test
    fun test() {
        repository.saveNewOne()

        connection.createStatement().use {
            val rs = it.executeQuery("SELECT COUNT(*) FROM responses")
            rs.next()
            val count = rs.getInt(1)
            Assertions.assertEquals(1, count)
        }
    }
}