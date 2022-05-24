package com.nicomahnic.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicomahnic.empanandascounterkt.data.repositories.EmpanadasRepository
import com.nicomahnic.empanandascounterkt.data.repositories.UsersRepository
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import com.nicomahnic.empanandascounterkt.models.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val empanadasRepo: EmpanadasRepository,
    private val userRepo: UsersRepository
): ViewModel() {

    var floatingButtonStatus: Boolean = false

    fun insertEmpanadas(empanadas: List<Empanada>, comment: String, user: User){
        viewModelScope.launch {
            empanadasRepo.insertList(empanadas, comment, user)
        }
    }

    suspend fun getAllUsers() = userRepo.getAllUsers()

}