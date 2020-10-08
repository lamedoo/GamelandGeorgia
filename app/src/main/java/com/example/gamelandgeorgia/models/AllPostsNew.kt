package com.example.gamelandgeorgia.models

data class AllPostsNew(
    val _links: Links,
    val content: Content,
    val jetpack_featured_media_url: String,
    val id: Int,
    val title: Title
) {
    data class Links(
        val author: List<Author>,
        val replies: List<Reply>
    ) {
        data class Author(
            val embeddable: Boolean,
            val href: String
        )

        data class Reply(
            val embeddable: Boolean,
            val href: String
        )
    }

    data class Content(
        val `protected`: Boolean,
        val rendered: String
    )

    data class Title(
        val rendered: String
    )
}