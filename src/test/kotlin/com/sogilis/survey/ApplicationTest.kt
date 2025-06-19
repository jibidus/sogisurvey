package com.sogilis.com.sogilis.survey


import com.sogilis.survey.DbExtension
import com.sogilis.survey.installModules
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions
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
    fun home() = testApplication {
        application {
            installModules(database.connection)
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(response.bodyAsText(), "Sondage")
    }

    @Test
    fun postSurvey() = testApplication {
        application {
            installModules(database.connection)
        }
        val response = client.post("/")
        assertEquals(HttpStatusCode.OK, response.status)
        database.connection.createStatement().use {
            val rs = it.executeQuery("SELECT COUNT(*) FROM responses")
            rs.next()
            val count = rs.getInt(1)
            Assertions.assertEquals(1, count)
        }
        assertContains(response.bodyAsText(), "Réponse enregistrée")
    }
}