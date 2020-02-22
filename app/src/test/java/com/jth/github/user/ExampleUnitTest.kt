package com.jth.github.user

import com.jth.github.user.repo.model.User
import com.jth.github.user.repo.api.GitHubUserApi
import com.jth.github.user.repo.api.ServiceGenerator
import org.junit.Test

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun searchUser() {
        val service = ServiceGenerator.createService(GitHubUserApi::class.java)
        val request = service.getSearchUser("jth")
        request.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (response.isSuccessful) {
                    val result = response.body()
                    print(result)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                println(t.message)
            }
        })
    }
}
