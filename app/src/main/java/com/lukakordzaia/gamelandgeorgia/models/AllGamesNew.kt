package com.lukakordzaia.gamelandgeorgia.models


import com.google.gson.annotations.SerializedName


data class AllGamesNew(
    @SerializedName("consoles")
        val consoles: List<Int>,
    @SerializedName("date")
        val date: String,
    @SerializedName("date_gmt")
        val dateGmt: String,
    @SerializedName("developers")
        val developers: List<Int>,
    @SerializedName("_embedded")
        val embedded: Embedded,
    @SerializedName("featured_media")
        val featuredMedia: Int,
    @SerializedName("franchises")
        val franchises: List<Int>,
    @SerializedName("genres")
        val genres: List<Int>,
    @SerializedName("guid")
        val guid: Guid,
    @SerializedName("id")
        val id: Int,
    @SerializedName("link")
        val link: String,
    @SerializedName("_links")
        val links: Links,
    @SerializedName("modified")
        val modified: String,
    @SerializedName("modified_gmt")
        val modifiedGmt: String,
    @SerializedName("parent")
        val parent: Int,
    @SerializedName("publishers")
        val publishers: List<Int>,
    @SerializedName("slug")
        val slug: String,
    @SerializedName("status")
        val status: String,
    @SerializedName("template")
        val template: String,
    @SerializedName("title")
        val title: Title,
    @SerializedName("game_date")
        val game_date: String,
    @SerializedName("type")
        val type: String
    ) {
        data class Embedded(
            @SerializedName("wp:featuredmedia")
            val wpFeaturedmedia: List<WpFeaturedmedia>,
            @SerializedName("wp:term")
            val wpTerm: List<List<WpTerm>>
        ) {
            data class WpFeaturedmedia(
                @SerializedName("alt_text")
                val altText: String,
                @SerializedName("author")
                val author: Int,
                @SerializedName("caption")
                val caption: Caption,
                @SerializedName("date")
                val date: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("link")
                val link: String,
                @SerializedName("_links")
                val links: Links,
                @SerializedName("media_details")
                val mediaDetails: MediaDetails,
                @SerializedName("media_type")
                val mediaType: String,
                @SerializedName("mime_type")
                val mimeType: String,
                @SerializedName("slug")
                val slug: String,
                @SerializedName("source_url")
                val sourceUrl: String,
                @SerializedName("title")
                val title: Title,
                @SerializedName("type")
                val type: String
            ) {
                data class Caption(
                    @SerializedName("rendered")
                    val rendered: String
                )

                data class Links(
                    @SerializedName("about")
                    val about: List<About>,
                    @SerializedName("author")
                    val author: List<Author>,
                    @SerializedName("collection")
                    val collection: List<Collection>,
                    @SerializedName("replies")
                    val replies: List<Reply>,
                    @SerializedName("self")
                    val self: List<Self>
                ) {
                    data class About(
                        @SerializedName("href")
                        val href: String
                    )

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

                    data class Reply(
                        @SerializedName("embeddable")
                        val embeddable: Boolean,
                        @SerializedName("href")
                        val href: String
                    )

                    data class Self(
                        @SerializedName("href")
                        val href: String
                    )
                }

                data class MediaDetails(
                    @SerializedName("file")
                    val `file`: String,
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("image_meta")
                    val imageMeta: ImageMeta,
                    @SerializedName("sizes")
                    val sizes: Sizes,
                    @SerializedName("width")
                    val width: Int
                ) {
                    data class ImageMeta(
                        @SerializedName("aperture")
                        val aperture: String,
                        @SerializedName("camera")
                        val camera: String,
                        @SerializedName("caption")
                        val caption: String,
                        @SerializedName("copyright")
                        val copyright: String,
                        @SerializedName("created_timestamp")
                        val createdTimestamp: String,
                        @SerializedName("credit")
                        val credit: String,
                        @SerializedName("focal_length")
                        val focalLength: String,
                        @SerializedName("iso")
                        val iso: String,
                        @SerializedName("keywords")
                        val keywords: List<Any>,
                        @SerializedName("orientation")
                        val orientation: String,
                        @SerializedName("shutter_speed")
                        val shutterSpeed: String,
                        @SerializedName("title")
                        val title: String
                    )

                    data class Sizes(
                        @SerializedName("blog-thumb")
                        val blogThumb: BlogThumb,
                        @SerializedName("full")
                        val full: Full,
                        @SerializedName("medium")
                        val medium: Medium,
                        @SerializedName("thumbnail")
                        val thumbnail: Thumbnail
                    ) {
                        data class BlogThumb(
                            @SerializedName("file")
                            val `file`: String,
                            @SerializedName("height")
                            val height: Int,
                            @SerializedName("mime_type")
                            val mimeType: String,
                            @SerializedName("source_url")
                            val sourceUrl: String,
                            @SerializedName("width")
                            val width: Int
                        )

                        data class Full(
                            @SerializedName("file")
                            val `file`: String,
                            @SerializedName("height")
                            val height: Int,
                            @SerializedName("mime_type")
                            val mimeType: String,
                            @SerializedName("source_url")
                            val sourceUrl: String,
                            @SerializedName("width")
                            val width: Int
                        )

                        data class Medium(
                            @SerializedName("file")
                            val `file`: String,
                            @SerializedName("height")
                            val height: Int,
                            @SerializedName("mime_type")
                            val mimeType: String,
                            @SerializedName("source_url")
                            val sourceUrl: String,
                            @SerializedName("width")
                            val width: Int
                        )

                        data class Thumbnail(
                            @SerializedName("file")
                            val `file`: String,
                            @SerializedName("height")
                            val height: Int,
                            @SerializedName("mime_type")
                            val mimeType: String,
                            @SerializedName("source_url")
                            val sourceUrl: String,
                            @SerializedName("width")
                            val width: Int
                        )
                    }
                }

                data class Title(
                    @SerializedName("rendered")
                    val rendered: String
                )
            }

            data class WpTerm(
                @SerializedName("id")
                val id: Int,
                @SerializedName("link")
                val link: String,
                @SerializedName("_links")
                val links: Links,
                @SerializedName("name")
                val name: String,
                @SerializedName("slug")
                val slug: String,
                @SerializedName("taxonomy")
                val taxonomy: String
            ) {
                data class Links(
                    @SerializedName("about")
                    val about: List<About>,
                    @SerializedName("collection")
                    val collection: List<Collection>,
                    @SerializedName("curies")
                    val curies: List<Cury>,
                    @SerializedName("self")
                    val self: List<Self>,
                    @SerializedName("wp:post_type")
                    val wpPostType: List<WpPostType>
                ) {
                    data class About(
                        @SerializedName("href")
                        val href: String
                    )

                    data class Collection(
                        @SerializedName("href")
                        val href: String
                    )

                    data class Cury(
                        @SerializedName("href")
                        val href: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("templated")
                        val templated: Boolean
                    )

                    data class Self(
                        @SerializedName("href")
                        val href: String
                    )

                    data class WpPostType(
                        @SerializedName("href")
                        val href: String
                    )
                }
            }
        }

        data class Guid(
            @SerializedName("rendered")
            val rendered: String
        )

        data class Links(
            @SerializedName("about")
            val about: List<About>,
            @SerializedName("collection")
            val collection: List<Collection>,
            @SerializedName("curies")
            val curies: List<Cury>,
            @SerializedName("predecessor-version")
            val predecessorVersion: List<PredecessorVersion>,
            @SerializedName("self")
            val self: List<Self>,
            @SerializedName("version-history")
            val versionHistory: List<VersionHistory>,
            @SerializedName("wp:attachment")
            val wpAttachment: List<WpAttachment>,
            @SerializedName("wp:featuredmedia")
            val wpFeaturedmedia: List<WpFeaturedmedia>,
            @SerializedName("wp:term")
            val wpTerm: List<WpTerm>
        ) {
            data class About(
                @SerializedName("href")
                val href: String
            )

            data class Collection(
                @SerializedName("href")
                val href: String
            )

            data class Cury(
                @SerializedName("href")
                val href: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("templated")
                val templated: Boolean
            )

            data class PredecessorVersion(
                @SerializedName("href")
                val href: String,
                @SerializedName("id")
                val id: Int
            )

            data class Self(
                @SerializedName("href")
                val href: String
            )

            data class VersionHistory(
                @SerializedName("count")
                val count: Int,
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

            data class WpTerm(
                @SerializedName("embeddable")
                val embeddable: Boolean,
                @SerializedName("href")
                val href: String,
                @SerializedName("taxonomy")
                val taxonomy: String
            )
        }

        data class Title(
            @SerializedName("rendered")
            val rendered: String
        )
    }