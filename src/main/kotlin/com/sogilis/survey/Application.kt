package com.sogilis.survey

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.sql.Connection
import java.sql.DriverManager

fun main(args: Array<String>) {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
        val url =
            System.getenv("DATABASE_URL") ?: "postgresql://localhost/jibidus?user=jibidus&ssl=false&port=5432"
        val conn: Connection = DriverManager.getConnection("jdbc:$url")
        resetDatabase(conn)
        installModules(conn)
    }.start(wait = true)
}

fun Application.installModules(connection: Connection) {
    configureSecurity()
    configureRouting(connection)
}

fun resetDatabase(connection: Connection) {
    connection.createStatement().use { stmt ->
        stmt.execute(
            """
            CREATE SCHEMA IF NOT EXISTS sogisurvey;
            DROP TABLE IF EXISTS sogisurvey.responses;
            CREATE TABLE IF NOT EXISTS sogisurvey.responses (
                id SERIAL PRIMARY KEY,
                author VARCHAR(255) NOT NULL,
                criterion VARCHAR(255) NOT NULL,
                priority SMALLINT NOT NULL,
                comment TEXT
            );
            """
        )
    }
}
