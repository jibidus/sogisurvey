package com.sogilis.survey

import com.sogilis.com.sogilis.survey.homePage
import com.sogilis.com.sogilis.survey.submittedSurveyPage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.logging.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Connection

internal val LOGGER = KtorSimpleLogger("com.sogilis.survey.Routing")

fun Application.configureRouting(connection: Connection, httpClient: HttpClient = applicationHttpClient) {
    val repository = ResponsesRepository(connection)
    routing {
        get("/") {
            val userSession: UserSession? = getSession(call)
            if (userSession != null) {
                getPersonalGreeting(call, httpClient, userSession)?.let {
                    call.respondHtml(block = homePage(currentUser = it, currentUri = call.request.uri))
                }
            }
        }
        post("/") {
            val userSession: UserSession? = getSession(call)
            if (userSession != null) {
                getPersonalGreeting(call, httpClient, userSession)?.let {
                    val values = call.receiveParameters()
                    val responses = CRITERIA.map { criterion ->
                        Priority(
                            criterionId = criterion.id,
                            value = values[criterion.id]!!.toInt(),
                            comment = values[criterion.commentId]
                        )
                    }.toSet()
                    repository.save(Response(it.name, responses))
                    val responsesCount = repository.count()
                    call.respondHtml(block = submittedSurveyPage(responsesCount))
                }
            }
        }
        get("/{path}") {
            val userSession: UserSession? = getSession(call)
            if (userSession != null) {
                getPersonalGreeting(call, httpClient, userSession)?.let {
                    call.respondText("Hello, ${it.name}!")
                }
            }
        }
    }
}

val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getPersonalGreeting(
    call: ApplicationCall,
    httpClient: HttpClient,
    userSession: UserSession
): UserInfo? {
    val response = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
        headers {
            append(HttpHeaders.Authorization, "Bearer ${userSession.token}")
        }
    }
    try {
        if (!response.status.isSuccess()) {
            LOGGER.warn("Cannot read userinfo from https://www.googleapis.com/oauth2/v2/userinfo (response status ${response.status} is not success):\n ${response.bodyAsText()}")
            val redirectUrl = URLBuilder("$baseUrl/login").run {
                parameters.append("redirectUrl", call.request.uri)
                build()
            }
            call.respondRedirect(redirectUrl)
            return null
        }
        return response.body()
    } catch (e: JsonConvertException) {
        throw RuntimeException(
            "Cannot read userinfo from responses returned by https://www.googleapis.com/oauth2/v2/userinfo:\n ${response.bodyAsText()}",
            e
        )
    }
}

suspend fun getSession(
    call: ApplicationCall
): UserSession? {
    val userSession: UserSession? = call.sessions.get()
    //if there is no session, redirect to login
    if (userSession == null) {
        val redirectUrl = URLBuilder("$baseUrl/login").run {
            parameters.append("redirectUrl", call.request.uri)
            build()
        }
        call.respondRedirect(redirectUrl)
        return null
    }
    return userSession
}


@Serializable
data class UserInfo(
    val id: String,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String? = null,
    val picture: String,
    val locale: String? = null,
)
