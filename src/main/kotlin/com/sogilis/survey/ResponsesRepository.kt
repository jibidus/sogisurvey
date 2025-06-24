package com.sogilis.survey

import java.sql.Connection

class ResponsesRepository(val conn: Connection) {
    fun count(): Int = conn.createStatement().use {
        val rs = it.executeQuery("SELECT COUNT(DISTINCT author) FROM sogisurvey.responses")
        rs.next()
        rs.getInt(1)
    }

    fun save(response: Response) {
        val sql = """
                INSERT INTO sogisurvey.responses
                (author, criterion, priority, comment)
                VALUES
           """.trimIndent() +
                response.priorities.joinToString(", ") {
                    "('${response.author}', '${it.criterionId}', ${it.value}, ${it.comment.toSQL()})"
                }
        conn.createStatement().use {
            it.execute(sql)
        }
    }
}

fun String?.toSQL() = if (this == null || this.isBlank()) {
    "NULL"
} else {
    "'$this'"
}