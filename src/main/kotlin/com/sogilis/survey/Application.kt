package com.sogilis.survey

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.sql.Connection
import java.sql.DriverManager


fun main(args: Array<String>) {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
        val url = System.getenv("DATABASE_URL") ?: "postgresql://localhost/test?user=fred&password=secret&ssl=true"
        val conn: Connection = DriverManager.getConnection("jdbc:$url")
        setupDatabase(conn)
        installModules(conn)
    }.start(wait = true)
}

fun Application.installModules(connection: Connection) {
    configureSecurity()
    configureRouting(connection)
}

fun setupDatabase(connection: Connection) {
    connection.createStatement().use { stmt ->
        stmt.execute(
            """
            CREATE SCHEMA IF NOT EXISTS sogisurvey;
            CREATE TABLE IF NOT EXISTS sogisurvey.responses (
                id SERIAL PRIMARY KEY,
                author VARCHAR(255) NOT NULL
            );
            """
        )
    }
}
