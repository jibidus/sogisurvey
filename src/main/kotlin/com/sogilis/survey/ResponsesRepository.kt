package com.sogilis.survey

import java.sql.Connection

class ResponsesRepository(val conn: Connection) {
    fun count(): Int = conn.createStatement().use {
        val rs = it.executeQuery("SELECT COUNT(id) FROM sogisurvey.responses")
        rs.next()
        rs.getInt(1)
    }

    fun save(response: Response) {
        // TODO: prevent sql injection
        conn.createStatement().use {
            val rs = it.executeQuery(
                """
                      INSERT INTO sogisurvey.responses
                      (author, comment)
                      VALUES
                      ('${response.author}', ${response.comment.toSQL()})
                      RETURNING id
                      """.trimIndent()
            )
            rs.next()
            val responseId = rs.getInt(1)

            val insertInto = """
                      INSERT INTO sogisurvey.priorities
                      (response_id, criterion_id, priority, comment)
                      VALUES
                      """.trimIndent()
            val values = response.priorities.joinToString(", ") { priority ->
                "($responseId, '${priority.criterionId}', ${priority.value}, ${priority.comment.toSQL()})"
            }
            it.execute(insertInto + values)
        }
    }
}

fun String?.toSQL() = if (this == null || this.isBlank()) {
    "NULL"
} else {
    "'$this'"
}