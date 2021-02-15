package com.polish.pocketmonsters.utils

import retrofit2.Response

sealed class DataState<out R> {
    data class Success<out T>(val data:T):DataState<T>()
    data class Error(
        val isNetworkError:Boolean,
        val errorCode:Int?,
        val errorBody: Response<Any>?
    ):DataState<Nothing>()
    object loading:DataState<Nothing>()
}