package com.sogilis.com.sogilis.survey

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
import kotlinx.html.footer
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.img
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.link
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.section
import kotlinx.html.style
import kotlinx.html.textArea
import kotlinx.html.title

fun homePage(currentUser: UserInfo, currentUri: String): HTML.() -> Unit = {
    head {
        meta(charset = "UTF-8")
        title("Sondage projet")
        // Documentation: https://picocss.com/
        link(href = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css", rel = "stylesheet")
    }
    body {
        header {
            // https://developers.google.com/identity/branding-guidelines
            style = "display: flex; justify-content: flex-end;"
            a(href = "https://accounts.google.com/AccountChooser", classes = "secondary") {
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
                projet/mission. Ainsi, dans un second temps, nous pourons voir comment améliorer l'adéquation entre ces attentes et les projets capturés par l'équipe commerciale.
                Biensûr tout ne sera pas possible, certaines contraintes externes comme le marché ou la capacité commerciale seront nos limites. Mais commençons par essayer.                                   
                               """.trimIndent()
                    }
                    article {
                        +"Il vous est demandé ici d'évaluer les critères ci-dessous du "
                        b {
                            +"moins importants (à gauche)"
                        }
                        +" au "
                        b {
                            +"plus important (à droite)"
                        }
                        +". N'hésitez à pas mettre plus de détails dans les champs associés (ex : ???)"
                    }
                }
            }
            main(classes = "container") {
                // add unique names to all inputs
                label {
                    +"Possibilité de travailler à distance (ex: chez moi ou depuis Sogilis)"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                        // aria-label="Détails"
                    }
                }


                label {
                    +"Confort de l'environnement de travail chez le client"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                    }
                }
                label {
                    +"Faibles contraintes sur l'environnement de dev"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: choix de l'OS, possibilité d'utiliser les outils Sogilis…"
                    }
                }
                label {
                    +"Localisation du client"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: proche gare, à l'ouest de Grenoble…"
                    }
                }
                label {
                    +"Type de gestion de projet"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: Itérations, Scrum, XP…"
                    }
                }
                label {
                    +"Autonomie"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                    }
                }
                label {
                    +"Durée de mission"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Longue ou courte, combien de mois/années …"
                    }
                }
                label {
                    +"Domaine métier"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: médical, éthique, IA…"
                    }
                }
                label {
                    +"Type de contrat"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: forfait, assistance Technique…"
                    }
                }
                label {
                    +"Base de code existante"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: partir de zéro, code existant de qualité, code legacy…"
                    }
                }
                label {
                    +"Contexte technique"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: front, web, mobile, embarqué, algo……"
                    }
                }
                label {
                    +"Language"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: language de haut niveau, language moderne…"
                    }
                }
                label {
                    +"Technos"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: React, Rails, Quarkus…"
                    }
                }
                label {
                    +"La mission me fait progresser rapidement"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Ex: techniquement, en pratiques, en compétences humaines…"
                    }
                }
                label {
                    +"Ambiance humaine"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                    }
                }
                label {
                    +"Pouvoir travailler en équipe Sogilis"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                    }
                }
                label {
                    +"Le client souhaite s'améliorer en continue"
                    input(type = range, name = "range")
                    input(type = text, name = "details") {
                        placeholder = "Plus de précision…"
                    }
                }

                textArea {
                    name = "comments"
                    placeholder = "Commentaires"
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
