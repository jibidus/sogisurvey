package com.sogilis.survey

data class Response(val author: String, val priorities: Set<Priority>, val comment: String?)

data class Priority(val criterionId: String, val value: Int, val comment: String?)