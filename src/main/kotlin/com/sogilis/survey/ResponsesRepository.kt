package com.sogilis.survey

import com.sogilis.survey.Database.PRIORITIES
import com.sogilis.survey.Database.RESPONSES
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
            .from(RESPONSES.table)
            .fetchOne()!!
            .value1()

    fun save(response: Response) {
        dsl.transaction { tx ->
            tx
                .dsl()
                .delete(PRIORITIES.table)
                .where(
                    PRIORITIES.RESPONSE_ID.`in`(
                        tx
                            .dsl()
                            .select(RESPONSES.ID)
                            .from(RESPONSES.table)
                            .where(RESPONSES.AUTHOR.equal(response.author)),
                    ),
                ).execute()

            tx
                .dsl()
                .delete(RESPONSES.table)
                .where(RESPONSES.AUTHOR.equal(response.author))
                .execute()

            val responseId =
                tx
                    .dsl()
                    .insertInto(RESPONSES.table, RESPONSES.AUTHOR, RESPONSES.COMMENT)
                    .values(response.author, response.comment)
                    .returning(RESPONSES.ID)
                    .fetchOne()!!
                    .getValue(RESPONSES.ID)

            val insert =
                tx.dsl().insertInto(
                    PRIORITIES.table,
                    PRIORITIES.RESPONSE_ID,
                    PRIORITIES.CRITERION_ID,
                    PRIORITIES.PRIORITY,
                    PRIORITIES.COMMENT,
                )
            response.priorities.forEach {
                insert.values(responseId, it.criterionId, it.value, it.comment)
            }
            insert.execute()
        }
    }
}
