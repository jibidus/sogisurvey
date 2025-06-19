package com.sogilis.com.sogilis.survey


import com.sogilis.installModules
import com.sogilis.survey.DbExtension
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.test.assertEquals

class ApplicationTest {

    companion object{
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
        assertEquals("Hello World!", response.bodyAsText())
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
    }
}