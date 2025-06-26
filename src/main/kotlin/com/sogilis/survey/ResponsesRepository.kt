package com.sogilis.survey

import com.sogilis.survey.Database.Priorities
import com.sogilis.survey.Database.Responses
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection

class ResponsesRepository(
    val conn: Connection,
) {
    private val dsl = DSL.using(conn, SQLDialect.POSTGRES)

    fun count(): Int =
        dsl
            .selectCount()
            .from(Responses.table)
            .fetch()
            .single()
            .value1()

    fun save(response: Response) {
        dsl
            .delete(Priorities.table)
            .where(
                Priorities.responseId.`in`(
                    dsl
                        .select(Responses.id)
                        .from(Responses.table)
                        .where(Responses.author.equal(response.author)),
                ),
            ).execute()

        dsl
            .delete(Responses.table)
            .where(Responses.author.equal(response.author))
            .execute()

        val responseId =
            dsl
                .insertInto(Responses.table, Responses.author, Responses.comment)
                .values(response.author, response.comment)
                .returning(Responses.id)
                .fetchOne()!!
                .getValue(Responses.id)

        val insert =
            dsl.insertInto(
                Priorities.table,
                Priorities.responseId,
                Priorities.criterionId,
                Priorities.priority,
                Priorities.comment,
            )
        response.priorities.forEach {
            insert.values(responseId, it.criterionId, it.value, it.comment)
        }
        insert.execute()
    }
}
