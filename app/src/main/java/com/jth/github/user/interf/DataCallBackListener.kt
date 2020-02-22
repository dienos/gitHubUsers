package com.jth.github.user.interf

interface DataCallBackListener {
    fun onSuccess(items: Any)
    fun onFailure(message: String?)
}