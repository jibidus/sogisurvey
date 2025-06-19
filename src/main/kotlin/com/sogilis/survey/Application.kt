package com.sogilis.survey

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.sql.Connection
import java.sql.DriverManager


fun main(args: Array<String>) {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
        val conn: Connection =
            DriverManager.getConnection("jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true")
        installModules(conn)
    }.start(wait = true)
}

fun Application.installModules(connection: Connection) {
    configureSecurity()
    configureRouting(connection)
}
