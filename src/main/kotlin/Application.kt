package com.sogilis

import io.ktor.server.application.*
import java.sql.Connection
import java.sql.DriverManager


fun main(args: Array<String>) {

    val conn: Connection = DriverManager.getConnection("jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true")
    // DO stuff
    conn.close()

    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {
    configureTemplating()
    configureSecurity()
    configureRouting()
}
