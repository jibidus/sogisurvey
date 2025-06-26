package com.sogilis.survey

data class CriterionInput(
    val id: String,
    val title: String,
    val commentPlaceholder: String,
) {
    val commentId = "$id-comment"
}

val CRITERIA =
    listOf(
        CriterionInput(
            id = "localisation",
            title = "📍 Localisation du client",
            commentPlaceholder = "Ex: proche gare, à l'ouest de Grenoble…",
        ),
        CriterionInput(
            id = "remote",
            title = "🏠 Travail à distance",
            commentPlaceholder = "Plus de précision…",
        ),
        CriterionInput(
            id = "duree-mission",
            title = "⏱️ Durée de mission",
            commentPlaceholder = "Longue ou courte, combien de mois/années …",
        ),
        CriterionInput(
            id = "type-contrat",
            title = "🔖 Type de contrat",
            commentPlaceholder = "Ex: forfait, assistance Technique…",
        ),
        CriterionInput(
            id = "amelioration-continue",
            title = "🔄 Le client souhaite s'améliorer et est à l'écoute de propositions",
            commentPlaceholder = "Plus de précision…",
        ),
        CriterionInput(
            id = "domaine-metier",
            title = "🏙️ Domaine métier",
            commentPlaceholder = "Ex: médical, éthique, IA…",
        ),
        CriterionInput(
            id = "autonomie",
            title = "⛵️ Autonomie pour adresser les besoins exprimés",
            commentPlaceholder = "Plus de précision…",
        ),
        CriterionInput(
            id = "progression",
            title = "🏃‍♀️ La mission me fait progresser rapidement",
            commentPlaceholder = "Ex: techniquement, en pratiques, en compétences humaines…",
        ),
        CriterionInput(
            id = "ambiance",
            title = "🎉 Ambiance avec le client",
            commentPlaceholder = "Plus de précision…",
        ),
        CriterionInput(
            id = "team-sogilis",
            title = "👫 Pouvoir travailler en équipe Sogilis",
            commentPlaceholder = "Plus de précision…",
        ),
        // dev
        CriterionInput(
            id = "gestion-projet",
            title = "🧑‍🍳 Type de gestion de projet",
            commentPlaceholder = "Ex: Itérations, Scrum, XP…",
        ),
        CriterionInput(
            id = "env-confort",
            title = "🐈 Confort de l'environnement de travail chez le client",
            commentPlaceholder = "Bureau attitré, salle de réunion disponible, locaux bien équipés…",
        ),
        CriterionInput(
            id = "dev-constraints",
            title = "👨🏻‍💻 Contraintes sur l'environnement de dev",
            commentPlaceholder = "Ex: choix de l'OS, accès aux outils Sogilis, utilisation de Teams…",
        ),
        CriterionInput(
            id = "base-code",
            title = "💻 Base de code existante",
            commentPlaceholder = "Ex: partir de zéro, code existant de qualité, code legacy…",
        ),
        CriterionInput(
            id = "contexte-technique",
            title = "🧑‍🔧 Contexte technique",
            commentPlaceholder = "Ex: front, web, mobile, embarqué, algo……",
        ),
        CriterionInput(
            id = "technos-langages",
            title = "🪠 Technos, langages",
            commentPlaceholder = "Ex: C++, Ruby, React, Rails, Quarkus…",
        ),
    )
