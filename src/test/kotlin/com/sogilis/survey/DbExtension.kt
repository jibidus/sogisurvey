package com.sogilis.survey

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer
import java.sql.Connection
import java.sql.DriverManager

class DbExtension : BeforeAllCallback, AfterAllCallback {

    private lateinit var postgresContainer: PostgreSQLContainer<*>
    lateinit var connection: Connection
    lateinit var repository: ResponsesRepository

    override fun beforeAll(context: ExtensionContext?) {
        postgresContainer = PostgreSQLContainer<Nothing>("postgres:16-alpine")
        postgresContainer.start()
        connection = DriverManager.getConnection(
            postgresContainer.jdbcUrl,
            postgresContainer.username,
            postgresContainer.password
        )
        repository = ResponsesRepository(connection)
    }

    override fun afterAll(context: ExtensionContext?) {
        connection.close()
        postgresContainer.stop()
    }
}