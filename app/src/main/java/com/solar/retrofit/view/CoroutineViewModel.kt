package com.solar.retrofit.view

import android.util.Log
import androidx.lifecycle.*
import com.solar.retrofit.NetworkClient
import com.solar.retrofit.response.UserItemResponse
import com.solar.retrofit.service.UserService
import kotlinx.coroutines.Dispatchers

class CoroutineViewModel : ViewModel() {
    private val service by lazy {
        NetworkClient.provideService<UserService>()
    }

    private val _loadList =  liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        try {
            val list = service.getUserList("kenneth").items.map { mapToUserView(it) }
            emit(list)
        } catch (e: Exception) {
            Log.d("CoroutineViewmodel", e.message?:"")
        }
    }
    val list: LiveData<List<UserView>> get() = _loadList


    fun getUser(q: String = "kenneth") {

    }

    private fun mapToUserView(type: UserItemResponse): UserView {
        return UserView(
            type.id ?: 0,
            type.login ?: "none",
            type.avatarUrl ?: "",
            type.url ?: ""
        )
    }
}