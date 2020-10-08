package com.example.gamelandgeorgia.models


import com.google.gson.annotations.SerializedName

data class AllPosts(
    @SerializedName("content")
    val content: Content,
    @SerializedName("featured_media")
    val featuredMedia: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("title")
    val title: Title
) {
    data class Content(
        @SerializedName("protected")
        val `protected`: Boolean,
        @SerializedName("rendered")
        val rendered: String
    )

    data class Links(
        @SerializedName("author")
        val author: List<Author>,
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("wp:attachment")
        val wpAttachment: List<WpAttachment>,
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<WpFeaturedmedia>
    ) {
        data class Author(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class Reply(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class WpAttachment(
            @SerializedName("href")
            val href: String
        )

        data class WpFeaturedmedia(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )
    }

    data class Title(
        @SerializedName("rendered")
        val rendered: String
    )
}