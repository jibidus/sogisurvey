package com.sogilis.survey

import org.jooq.impl.DSL.field

object ResponsesTable {
    const val NAME = "sogisurvey.responses"
    val comment = field("comment")
}

object PrioritiesTable {
    const val NAME = "sogisurvey.priorities"
    val comment = field("comment")
}
