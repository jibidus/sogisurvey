package com.sogilis.com.sogilis.survey

import kotlinx.html.HTML
import kotlinx.html.article
import kotlinx.html.b
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.hr
import kotlinx.html.link
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.style
import kotlinx.html.title

fun submittedSurveyPage(responsesCount: Int): HTML.() -> Unit =
    {
        head {
            meta(charset = "UTF-8")
            title("Sondage projet")
            // Documentation: https://picocss.com/
            link(href = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css", rel = "stylesheet")
        }
        body {
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
    }
