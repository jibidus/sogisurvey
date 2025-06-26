package com.sogilis.survey

import kotlinx.html.BODY
import kotlinx.html.HTML
import kotlinx.html.STYLE
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.style
import kotlinx.html.title

fun layout(
    additionalStyle: STYLE.() -> Unit = {},
    body: BODY.() -> Unit,
): HTML.() -> Unit =
    {
        head {
            meta(charset = "UTF-8")
            title("Sondage projet")
            // Documentation: https://picocss.com/
            link(href = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css", rel = "stylesheet")
            style {
                additionalStyle()
            }
        }
        body {
            body()
        }
    }
