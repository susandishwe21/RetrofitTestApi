package com.example.retrofttestapi.api

import com.example.retrofttestapi.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface PostApiInterface {
    @GET("posts")
    fun getPosts() : Call<List<Posts>>
}