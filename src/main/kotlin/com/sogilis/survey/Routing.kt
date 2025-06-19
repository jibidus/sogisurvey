package com.sogilis.survey

import com.sogilis.com.sogilis.survey.homePage
import com.sogilis.com.sogilis.survey.submittedSurveyPage
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import java.sql.Connection

fun Application.configureRouting(connection: Connection) {
    routing {
        get("/") {
            call.respondHtml(block = homePage)
        }
        post("/") {
            ResponsesRepository(connection).saveNewOne()
            call.respondHtml(block = submittedSurveyPage)
        }
    }
}

