package com.jth.github.user.repo

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.jth.github.user.interf.DataCallBackListener
import com.jth.github.user.repo.model.User
import com.jth.github.user.repo.api.GitHubUserApi
import com.jth.github.user.repo.api.ServiceGenerator
import com.jth.github.user.repo.model.Users
import com.jth.github.user.repo.model.UsersPagerData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    companion object {
        private var instance: MainRepository? = null

        fun getInstance(): MainRepository {
            if (instance == null) {
                instance = MainRepository()
            }

            return instance!!
        }
    }

    var searchUsers : Users = Users()
    var favoriteUsers : Users = Users()

    fun searchUser(user: String, listener: DataCallBackListener) {
        val service = ServiceGenerator.createService(GitHubUserApi::class.java)
        val request = service.getSearchUser(user)
        request.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val data = UsersPagerData()
                    val userList : ArrayList<User> = arrayListOf()
                    val favoriteList : ArrayList<User> = arrayListOf()

                    result?.apply {
                        val list : JsonArray  = asJsonObject.get("items").asJsonArray

                        list.forEach {
                            val user  = User()
                            val jsonObject = it.asJsonObject
                            user.id = jsonObject.get("id").asInt
                            user.username = jsonObject.get("login").asString
                            user.avatarUrl = jsonObject.get("avatar_url").asString
                            userList.add(user)
                        }
                    }

                    data.pagerList.add(userList)
                    data.pagerList.add(favoriteList)

                    listener.onSuccess(data)
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                listener.onFailure(t.message)
            }
        })
    }
}