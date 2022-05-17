package com.example.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empanandascounterkt.data.repositories.EmpanadasRepository
import com.example.empanandascounterkt.models.domain.Empanada
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val empanadasRepo: EmpanadasRepository
): ViewModel() {

    var floatingButtonStatus: Boolean = false

    fun insertEmpanadas(empanadas: List<Empanada>, comment: String){
        viewModelScope.launch {
            empanadasRepo.insertList(empanadas, comment)
        }
    }

}