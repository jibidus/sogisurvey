package com.sogilis.survey

import org.jooq.impl.DSL
import org.jooq.impl.DSL.field

object Database {
    init {
        System.setProperty("org.jooq.no-logo", "true")
    }

    object RESPONSES {
        val table = DSL.table("sogisurvey.responses")
        val ID = field("id")
        val AUTHOR = field("author")
        val COMMENT = field("comment")
    }

    object PRIORITIES {
        val table = DSL.table("sogisurvey.priorities")
        val RESPONSE_ID = field("response_id")
        val CRITERION_ID = field("criterion_id")
        val PRIORITY = field("priority")
        val COMMENT = field("comment")
    }
}
