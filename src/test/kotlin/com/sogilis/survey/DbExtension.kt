package com.sogilis.survey

import com.sogilis.survey.ResponseRepositoryTest.Companion.database
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer
import java.sql.Connection
import java.sql.DriverManager

class DbExtension :
    BeforeAllCallback,
    AfterAllCallback {
    private lateinit var postgresContainer: PostgreSQLContainer<*>
    lateinit var connection: Connection
    lateinit var repository: ResponsesRepository
    lateinit var dsl: DSLContext

    override fun beforeAll(context: ExtensionContext?) {
        postgresContainer = PostgreSQLContainer<Nothing>("postgres:17-alpine")
        postgresContainer.start()
        connection =
            DriverManager.getConnection(
                postgresContainer.jdbcUrl,
                postgresContainer.username,
                postgresContainer.password,
            )
        repository = ResponsesRepository(connection)
        dsl = DSL.using(database.connection, SQLDialect.POSTGRES)
    }

    override fun afterAll(context: ExtensionContext?) {
        connection.close()
        postgresContainer.stop()
    }
}
