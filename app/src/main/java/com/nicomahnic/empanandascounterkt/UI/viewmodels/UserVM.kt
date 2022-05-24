package com.nicomahnic.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicomahnic.empanandascounterkt.data.repositories.UsersRepository
import com.nicomahnic.empanandascounterkt.models.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userRepo: UsersRepository
): ViewModel() {

    suspend fun getAllUsers() = userRepo.getAllUsers()

    fun insertUser(user: User){
        viewModelScope.launch {
            userRepo.insertUser(user)
        }
    }

}