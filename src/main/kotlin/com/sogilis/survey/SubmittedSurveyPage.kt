package com.sogilis.com.sogilis.survey

import com.sogilis.survey.layout
import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.b
import kotlinx.html.hr
import kotlinx.html.main
import kotlinx.html.style

fun submittedSurveyPage(responsesCount: Int): HTML.() -> Unit =
    layout {
        main(classes = "container") {
            article {
                style = "text-align: center;"
                b {
                    +"🙏 Merci pour votre contribution, vos critères ont bien été enregistrés 🎉."
                }
                hr {}
                +"Pour modifier votre réponse, vous pouvez répondre de nouveau au sondage ou nous contacter directement. "
                +"Avec vous, $responsesCount personnes ont déjà répondu. Les résultats seront communiqués sur slack dans quelques jours. "
                +"L'équipe CSE."
            }
        }
    }
