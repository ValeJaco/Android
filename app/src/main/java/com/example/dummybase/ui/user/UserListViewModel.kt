package com.example.dummybase.ui.user

import android.view.View
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
import com.google.android.material.snackbar.Snackbar

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository ) : ViewModel() {


    val isLoading: ObservableBoolean = ObservableBoolean(true)
    lateinit var baseView : View;

    private var _userLiveData: MutableLiveData<Int> = MutableLiveData()
    val userLiveData: LiveData<User?> = _userLiveData.switchMap {
        userRepository.getUserById( it ).map { result ->
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
            result.data?.let { it1 -> println( it1.lastname ) }
            result.data
        }
    }

    private var _userListLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
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

    fun loadUserDetail(userId: Int) {
        _userLiveData.postValue(userId)
    }

    fun showSnackBar( ) {

        Snackbar.make( baseView , "Je suis snack bar !!!", 2500 )
            .setAction("Oh yeah action") {
                // Responds to click on the action
            }
            .show()
    }
}