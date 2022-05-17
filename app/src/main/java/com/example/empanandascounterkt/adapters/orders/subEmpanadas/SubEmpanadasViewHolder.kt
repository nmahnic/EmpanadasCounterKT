package com.example.empanandascounterkt.adapters.orders.subEmpanadas

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.ItemOrdersBinding
import com.example.empanandascounterkt.databinding.ItemUsersBinding
import com.example.empanandascounterkt.databinding.SubItemEmpandasBinding
import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.utils.Utils

class SubEmpanadasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = SubItemEmpandasBinding.bind(view)


    fun render(empanada: Empanada){
        binding.tvName.text = empanada.name
        binding.tvCounter.text = empanada.count.toString()
    }
}