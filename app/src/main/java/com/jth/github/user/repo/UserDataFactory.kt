package com.jth.github.user.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jth.github.user.repo.model.User

class UserDataFactory: DataSource.Factory<Int, User>() {

    var postLiveData : MutableLiveData<UserPositionalDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, User> {
        val repo = MainRepository.getInstance()

        val dataSource = UserPositionalDataSource( repo.searchUsers.users.filterIndexed {
                i, _ -> i <= 100
        } , repo.searchUsers.users)

        postLiveData = MutableLiveData()
        postLiveData.postValue(dataSource)

        return dataSource
    }
}