package com.jth.github.user.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jth.github.user.repo.model.User

class FavoriteUserDataFactory: DataSource.Factory<Int, User>() {

    private var postLiveData : MutableLiveData<UserPositionalDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, User> {
        val repo = MainRepository.getInstance()

        val dataSource = UserPositionalDataSource( repo.favoriteUsers.users.filterIndexed {
                i, _ -> i <= 100
        } , repo.favoriteUsers.users)

        postLiveData = MutableLiveData()
        postLiveData.postValue(dataSource)

        return dataSource
    }
}