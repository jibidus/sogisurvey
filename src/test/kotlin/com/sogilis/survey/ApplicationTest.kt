package com.sogilis.com.sogilis.survey

import com.sogilis.survey.DbExtension
import com.sogilis.survey.installModules
import com.sogilis.survey.resetDatabase
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {
    companion object {
        @JvmField
        @RegisterExtension
        var database = DbExtension()
    }

    @Test
    fun home() =
        testApplication {
            application {
                installModules(database.connection)
                resetDatabase(database.connection)
            }
            val response = client.get("/")
            assertEquals(HttpStatusCode.OK, response.status)
            assertContains(response.bodyAsText(), "Sondage")
        }

    @Test
    fun submittedSurvey() =
        testApplication {
            application {
                installModules(database.connection)
                resetDatabase(database.connection)
            }
            val response = client.post("/")
            assertEquals(HttpStatusCode.OK, response.status)
            assertContains(response.bodyAsText(), "1 réponse(s) enregistrée(s)")
        }
}
