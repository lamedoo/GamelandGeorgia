package com.lukakordzaia.gamelandgeorgia.models

import com.google.gson.annotations.SerializedName

data class Comments (
    @SerializedName("author")
    val author: Int,
    @SerializedName("author_avatar_urls")
    val authorAvatarUrls: AuthorAvatarUrls,
    @SerializedName("author_name")
    val authorName: String,
    @SerializedName("author_url")
    val authorUrl: String,
    @SerializedName("content")
    val content: Content,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("parent")
    val parent: Int,
    @SerializedName("type")
    val type: String
) {
    data class AuthorAvatarUrls(
        @SerializedName("24")
        val x24: String,
        @SerializedName("48")
        val x48: String,
        @SerializedName("96")
        val x96: String
    )

    data class Content(
        @SerializedName("rendered")
        val rendered: String
    )

    data class Links(
        @SerializedName("author")
        val author: List<Author>,
        @SerializedName("collection")
        val collection: List<Collection>,
        @SerializedName("self")
        val self: List<Self>,
        @SerializedName("up")
        val up: List<Up>
    ) {
        data class Author(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String
        )

        data class Collection(
            @SerializedName("href")
            val href: String
        )

        data class Self(
            @SerializedName("href")
            val href: String
        )

        data class Up(
            @SerializedName("embeddable")
            val embeddable: Boolean,
            @SerializedName("href")
            val href: String,
            @SerializedName("post_type")
            val postType: String
        )
    }
}

data class AddComments (
    @SerializedName("author")
    val author: Int,
    @SerializedName("content")
    val content: String
)