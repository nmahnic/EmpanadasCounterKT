package com.example.empanandascounterkt.adapters.orders.subEmpanadas

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.SubItemEmpandasBinding
import com.example.empanandascounterkt.models.domain.Empanada

class SubEmpanadasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = SubItemEmpandasBinding.bind(view)


    fun render(empanada: Empanada){
        binding.tvName.text = empanada.name
        binding.tvCounter.text = empanada.quantity.toString()
    }
}