package com.sogilis.survey

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.article
import kotlinx.html.h2
import kotlinx.html.li
import kotlinx.html.main
import kotlinx.html.ul
import org.jooq.impl.DSL
import java.sql.Connection

object GetParticipants {
    fun configureOn(
        application: Application,
        connection: Connection,
    ) {
        application.routing {
            get("/participants") {
                val participants =
                    DSL
                        .using(connection)
                        .select(Database.RESPONSES.AUTHOR)
                        .from(Database.RESPONSES.table)
                        .orderBy(Database.RESPONSES.AUTHOR)
                        .query
                        .map {
                            it.getValue(Database.RESPONSES.AUTHOR)
                        }
                call.respondHtml(block = showParticipants(participants))
            }
        }
    }

    fun showParticipants(participants: List<String>) =
        layout {
            main(classes = "container") {
                article {
                    h2 {
                        +"Personnes ayant répondu au sondege"
                    }
                    ul {
                        participants.forEach {
                            li {
                                +it
                            }
                        }
                    }
                }
            }
        }
}
