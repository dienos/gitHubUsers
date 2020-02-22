package com.jth.github.user.repo.model

import androidx.lifecycle.MutableLiveData

class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}