package com.sogilis.survey

import org.jooq.impl.DSL
import org.jooq.impl.DSL.field

object Database {
    init {
        System.setProperty("org.jooq.no-logo", "true")
    }

    object Responses {
        val table = DSL.table("sogisurvey.responses")
        val id = field("id")
        val author = field("author")
        val comment = field("comment")
    }

    object Priorities {
        val table = DSL.table("sogisurvey.priorities")
        val responseId = field("response_id")
        val criterionId = field("criterion_id")
        val priority = field("priority")
        val comment = field("comment")
    }
}
