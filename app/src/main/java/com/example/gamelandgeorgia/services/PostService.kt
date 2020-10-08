package com.example.gamelandgeorgia.services

import com.example.gamelandgeorgia.models.AllPosts
import com.example.gamelandgeorgia.models.AllPostsNew
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("posts?_fields=id,title,_links,jetpack_featured_media_url")
    fun getAllPostList (@Query("page") page: Int) : Call<MutableList<AllPostsNew>>
}