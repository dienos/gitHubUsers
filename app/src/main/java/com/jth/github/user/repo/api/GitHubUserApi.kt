package com.jth.github.user.repo.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface GitHubUserApi {
    @GET("/search/users")
    fun getSearchUser(@Query("q") user : String): Call<JsonElement>
}

