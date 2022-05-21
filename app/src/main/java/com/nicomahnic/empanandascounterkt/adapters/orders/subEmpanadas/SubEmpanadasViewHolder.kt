package com.nicomahnic.empanandascounterkt.adapters.orders.subEmpanadas

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.databinding.SubItemEmpandasBinding
import com.nicomahnic.empanandascounterkt.models.domain.Empanada

class SubEmpanadasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = SubItemEmpandasBinding.bind(view)


    fun render(empanada: Empanada){
        binding.tvName.text = empanada.name
        binding.tvCounter.text = empanada.quantity.toString()
    }
}