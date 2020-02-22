package com.jth.github.user.repo

import androidx.paging.PositionalDataSource
import com.jth.github.user.repo.model.User

class UserPositionalDataSource(private val initRooms : List<User>, private val users: ArrayList<User>) :
    PositionalDataSource<User>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User>) {
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize))
    }

    private fun loadRangeInternal(startPosition: Int, loadCount: Int): ArrayList<User> {
        val modelList: ArrayList<User> = arrayListOf()
        val endPosition = Math.min(computeCount(), startPosition + loadCount)
        for (i in startPosition until endPosition) {
            modelList.add(users[i])
        }
        return modelList
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User>) {
        val totalCount = initRooms.size
        val position = computeInitialLoadPosition(params, totalCount)
        callback.onResult(initRooms, position, totalCount)
    }

    private fun computeCount(): Int {
        return users.size
    }
}