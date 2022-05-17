package com.example.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.example.empanandascounterkt.data.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userRepo: UsersRepository
): ViewModel() {

    suspend fun getAllUsers() = userRepo.getAllUsers()

}