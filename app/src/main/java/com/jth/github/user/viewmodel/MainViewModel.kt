package com.jth.github.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jth.github.user.interf.DataCallBackListener
import com.jth.github.user.repo.FavoriteUserDataFactory
import com.jth.github.user.repo.MainRepository
import com.jth.github.user.repo.UserDataFactory
import com.jth.github.user.repo.model.NotNullMutableLiveData
import com.jth.github.user.repo.model.User
import com.jth.github.user.repo.model.UsersPagerData
import com.jth.github.user.usecase.MainActivityUseCase

class MainViewModel(private val useCase: MainActivityUseCase, val repo: MainRepository) : ViewModel() {
    var userDataFactory: UserDataFactory? = null
    var favoriteUserDataFactory: FavoriteUserDataFactory? = null

    val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(100)
        .setPageSize(12)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    var userPagerData : NotNullMutableLiveData<UsersPagerData> = NotNullMutableLiveData(UsersPagerData())

    fun searchUser(user : String) {
        repo.searchUser(user, object : DataCallBackListener {
            override fun onSuccess(items: Any) {
                if(items is UsersPagerData) {
                    userPagerData.value = items

                    items.pagerList.forEachIndexed {
                        i, item ->

                        if(i == 0) {
                            repo.searchUsers.users = item
                        } else {
                            repo.favoriteUsers.users = item
                        }
                    }
                }
            }

            override fun onFailure(message: String?) {

            }
        })
    }

    fun getSearchItem(): LiveData<PagedList<User>> {
        val factory = UserDataFactory()
        userDataFactory = factory
        return LivePagedListBuilder(factory, config).build()
    }

    fun getFavoriteItem(): LiveData<PagedList<User>> {
        val factory = FavoriteUserDataFactory()
        favoriteUserDataFactory = factory
        return LivePagedListBuilder(factory, config).build()
    }
}