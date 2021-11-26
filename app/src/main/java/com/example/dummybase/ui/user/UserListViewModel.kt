package com.example.dummybase.ui.user

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.example.dummybase.api.ApiResult
import com.example.dummybase.data.model.User
import com.example.dummybase.data.repository.UserRepository
import com.example.dummybase.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject
import com.example.dummybase.api.ApiResult.Status.*

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository ) : ViewModel() {

    // private var _userListLiveData: MutableLiveData<List<User>> = MutableLiveData()

    val isLoading: ObservableBoolean = ObservableBoolean(true)
    private var _userListLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
    // val userListLiveData: LiveData<List<User>> = _userListLiveData

    val userListLiveData: LiveData<List<User>?> = _userListLiveData.switchMap {
        userRepository.observeUserList().map { result ->
            when (result.status) {
                SUCCESS -> {
                    isLoading.set(false)
                }
                ERROR -> {
                    isLoading.set(false)
                    Timber.e("Error fetching users : ${result.message}")
                }
                LOADING -> {
                    isLoading.set(true)
                }
            }
            result.data
        }
    }

    fun loadUsers() {
        _userListLiveData.postValue(Event(Unit))
    }

}