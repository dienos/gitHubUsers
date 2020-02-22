package com.jth.github.user.repo.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("login")
    var username: String = "",

    @SerializedName("avatar_url")
    var avatarUrl: String = "",

    var isFavorite: Boolean = false
)