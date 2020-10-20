package com.lukakordzaia.gamelandgeorgia.services

import com.lukakordzaia.gamelandgeorgia.models.*
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("posts?_fields=id,title,excerpt,jetpack_featured_media_url,date,post_video,link")
    fun getAllPostList (@Query("page") page: Int) : Call<MutableList<AllPostsNew>>

    @GET("posts?categories=54&_fields=id,title,excerpt,jetpack_featured_media_url,post_ratings_average,link")
    fun getAllReviewList (@Query("page") page: Int) : Call<MutableList<AllPostsNew>>

    @GET("posts?categories=79,103&_fields=id,title,excerpt,jetpack_featured_media_url,date,post_video,link")
    fun getAllVideoList (@Query("page") page: Int) : Call<MutableList<AllPostsNew>>

    @GET("posts/{id}?_embed")
    fun getSinglePost (@Path("id") id: String) : Call<AllPostsNew>

    @GET("game?_embed")
    fun getSingleGame (@Query("slug") slug: String) : Call<List<AllGamesNew>>

    @GET("comments?")
    fun getPostComment (@Query("post") post: String) : Call<MutableList<Comments>>

    @POST("https://gameland.ge/wp-json/jwt-auth/v1/token")
    fun getUser (@Body userInfo: getUser) : Call <UserResponse>

    @POST("comments?")
    fun addComment (@Header("Authorization") authorization: String, @Query("post") post: String, @Body commentInfo: AddComments) : Call <AddComments>

    @GET("users/{id}")
    fun getSingleProfile (@Path("id") id: String) : Call<UserInfo>
}