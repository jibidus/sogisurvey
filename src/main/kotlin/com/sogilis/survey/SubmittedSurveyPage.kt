package com.sogilis.com.sogilis.survey

import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.title

fun submittedSurveyPage(responsesCount: Int): HTML.() -> Unit = {
    head {
        meta(charset = "UTF-8")
        title("Sondage projet")
        // Documentation: https://picocss.com/
        linkedMapOf("stylesheet" to "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css")
    }
    body {
        main(classes = "container") {
            article {
                +"$responsesCount réponse(s) enregistrée(s)"
            }
        }
    }
}