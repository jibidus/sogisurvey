package com.sogilis.survey

data class Response(val author: String, val priorities: Set<Priority>)

data class Priority(val criterionId: String, val value: Int, val comment: String?)