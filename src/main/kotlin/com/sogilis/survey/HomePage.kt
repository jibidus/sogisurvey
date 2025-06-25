package com.sogilis.com.sogilis.survey

import com.sogilis.survey.CRITERIA
import com.sogilis.survey.UserInfo
import kotlinx.html.FormMethod
import kotlinx.html.HTML
import kotlinx.html.InputType.range
import kotlinx.html.InputType.submit
import kotlinx.html.InputType.text
import kotlinx.html.a
import kotlinx.html.article
import kotlinx.html.b
import kotlinx.html.body
import kotlinx.html.dataList
import kotlinx.html.footer
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.link
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.option
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.textArea
import kotlinx.html.title

// TODO Use CSS DSL (see https://ktor.io/docs/server-css-dsl.html)
fun homePage(currentUser: UserInfo, currentUri: String): HTML.() -> Unit = {
    head {
        meta(charset = "UTF-8")
        title("Sondage projet")
        // Documentation: https://picocss.com/
        link(href = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css", rel = "stylesheet")
        style {
            +"""
              .criterion-ordinal {
                color: var(--pico-muted-color);
              }
              
              input[type='range'] {
                margin-bottom: 0rem;
              }
              
              datalist {
                display: flex;
                justify-content: space-between;
                height: auto;
                overflow: hidden;
                margin-bottom: 1rem;
              
                option {
                  color: var(--pico-muted-color);
                  font-size: 0.8em;
                }
                
                option:before {
                  content: '';
                  display: block;
                  width: 0;
                  height: auto;
                  padding-left: 3px;
                  text-indent: 0;
                }
              }
            """.trimIndent()
        }
    }
    body {
        header {
            // https://developers.google.com/identity/branding-guidelines
            style = "display: flex; justify-content: flex-end;"
            a(href = "https://accounts.google.com/AccountChooser", classes = "secondary") {
                style = "margin-right: 2rem;"
                attributes.put("data-tooltip", "Utilisateur connecté: ${currentUser.name}")
                attributes.put("data-placement", "left")
                img {
                    src = currentUser.picture
                    style = "margin-right: 0.5em"
                    width = "32em"
                }
                +"Changer d'utilisateur"
            }
        }
        form(method = FormMethod.post, action = "/") {
            header(classes = "container") {
                h1 { +"Qu'est-ce qu'un \"bon\" projet ?" }
                section {
                    article {
                        +"""
                Ce sondage a pour objectif d'affiner notre compréhension de ce qu'attend l'équipe de devs en terme de
                projet/mission. Ainsi, dans un second temps, nous pourons voir comment améliorer l'adéquation entre ces attentes et les projets identifiés par l'équipe commerciale.
                Biensûr tout ne sera pas possible, certaines contraintes comme la situation du marché actuel seront nos limitantes. Mais commençons par essayer.                                   
                               """.trimIndent()
                    }
                    article {
                        +"Il vous est demandé ici d'évaluer une liste de critères du "
                        b {
                            +"moins importants"
                        }
                        +" au "
                        b {
                            +"plus important"
                        }
                        +" pour vous. N'hésitez à pas remplir les champs libres afin que l'on puisse cerner au mieux vos attentes."
                    }
                    article {
                        +"Dernière chose, c'est principalement l'ordre relatif des critères compte, c'est pour cela qu'il n'y a pas d'échelle."
                    }
                }
            }
            main(classes = "container") {
                CRITERIA.forEachIndexed { index, criterion ->
                    label {
                        style = "margin-bottom: 2rem;"
                        span(classes = "criterion-ordinal") {
                            +"${index + 1} - "
                        }
                        span {
                            +"${criterion.title} :"
                        }
                        input(type = range, name = criterion.id) {
                            list = "values-for-${criterion.id}"
                        }
                        dataList {
                            id = "values-for-${criterion.id}"
                            option {
                                value = "0"
                                label = "Peu important"
                            }
                            option {
                                value = "100"
                                label = "Très important"
                            }
                        }
                        input(type = text, name = criterion.commentId) {
                            placeholder = criterion.commentPlaceholder
                        }
                    }
                }

                label {
                    htmlFor = "global-comments"
                    +"Commentaire global :"
                }
                textArea {
                    id = "global-comments"
                    name = "global-comments"
                    placeholder = "Ex: autres critères"
                }
            }
            footer(classes = "container") {
                input(type = submit) {
                    value = "Envoyer"
                }
            }
        }
    }
}
