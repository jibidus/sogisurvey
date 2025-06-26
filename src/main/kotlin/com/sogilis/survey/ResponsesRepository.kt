package com.sogilis.survey

import com.sogilis.survey.Database.Priorities
import com.sogilis.survey.Database.Responses
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection

class ResponsesRepository(
    conn: Connection,
) {
    private val dsl = DSL.using(conn, SQLDialect.POSTGRES)

    fun count(): Int =
        dsl
            .selectCount()
            .from(Responses.table)
            .fetchOne()!!
            .value1()

    fun save(response: Response) {
        dsl.transaction { tx ->
            tx
                .dsl()
                .delete(Priorities.table)
                .where(
                    Priorities.responseId.`in`(
                        tx
                            .dsl()
                            .select(Responses.id)
                            .from(Responses.table)
                            .where(Responses.author.equal(response.author)),
                    ),
                ).execute()

            tx
                .dsl()
                .delete(Responses.table)
                .where(Responses.author.equal(response.author))
                .execute()

            val responseId =
                tx
                    .dsl()
                    .insertInto(Responses.table, Responses.author, Responses.comment)
                    .values(response.author, response.comment)
                    .returning(Responses.id)
                    .fetchOne()!!
                    .getValue(Responses.id)

            val insert =
                tx.dsl().insertInto(
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
}
