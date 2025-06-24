package com.sogilis.survey


data class CriterionInput(
    val id: String,
    val title: String,
    val commentPlaceholder: String
) {
    val commentId = "$id-comment"
}

val CRITERIA = listOf(
    CriterionInput(
        id = "env-confort", title = "Confort de l'environnement de travail chez le client",
        commentPlaceholder = "Plus de précision…"
    ),
    CriterionInput(
        id = "dev-constraints", title = "Faibles contraintes sur l'environnement de dev",
        commentPlaceholder = "Ex: choix de l'OS, possibilité d'utiliser les outils Sogilis…"
    ),
    CriterionInput(
        id = "localisation", title = "Localisation du client",
        commentPlaceholder = "Ex: proche gare, à l'ouest de Grenoble…"
    ),
    CriterionInput(
        id = "gestion-projet", title = "Type de gestion de projet",
        commentPlaceholder = "Ex: Itérations, Scrum, XP…"
    ),
    CriterionInput(
        id = "autonomie", title = "Autonomie",
        commentPlaceholder = "Plus de précision…"
    ),
    CriterionInput(
        id = "duree-mission", title = "Durée de mission",
        commentPlaceholder = "Longue ou courte, combien de mois/années …"
    ),
    CriterionInput(
        id = "domaine-metier", title = "Domaine métier",
        commentPlaceholder = "Ex: médical, éthique, IA…"
    ),
    CriterionInput(
        id = "type-contrat", title = "Type de contrat",
        commentPlaceholder = "Ex: forfait, assistance Technique…"
    ),
    CriterionInput(
        id = "base-code", title = "Base de code existante",
        commentPlaceholder = "Ex: partir de zéro, code existant de qualité, code legacy…"
    ),
    CriterionInput(
        id = "contexte-technique", title = "Contexte technique",
        commentPlaceholder = "Ex: front, web, mobile, embarqué, algo……"
    ),
    CriterionInput(
        id = "language", title = "Language",
        commentPlaceholder = "Ex: language de haut niveau, language moderne…"
    ),
    CriterionInput(
        id = "technos", title = "Technos",
        commentPlaceholder = "Ex: React, Rails, Quarkus…"
    ),
    CriterionInput(
        id = "progression", title = "La mission me fait progresser rapidement",
        commentPlaceholder = "Ex: techniquement, en pratiques, en compétences humaines…"
    ),
    CriterionInput(
        id = "ambiance", title = "Ambiance humaine",
        commentPlaceholder = "Plus de précision…"
    ),
    CriterionInput(
        id = "team-sogilis", title = "Pouvoir travailler en équipe Sogilis",
        commentPlaceholder = "Plus de précision…"
    ),
    CriterionInput(
        id = "amelioration-continue", title = "Le client souhaite s'améliorer en continue",
        commentPlaceholder = "Plus de précision…"
    ),
)
