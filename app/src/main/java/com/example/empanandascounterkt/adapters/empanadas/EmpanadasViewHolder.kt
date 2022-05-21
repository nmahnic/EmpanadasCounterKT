package com.example.empanandascounterkt.adapters.empanadas


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.ItemEmpandasBinding
import com.example.empanandascounterkt.models.domain.Empanada

class EmpanadasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemEmpandasBinding.bind(view)


    fun render(empanada: Empanada, onClickListener: EmpanadasAdapter.BtnListener){
        binding.tvName.text = empanada.name
        binding.tvCounter.text = empanada.quantity.toString()

        binding.btnMinus.setOnClickListener {
            empanada.quantity = empanada.quantity - 1
            binding.tvCounter.text = empanada.quantity.toString()
            onClickListener.onBtnClick( empanada )
        }

        binding.btnPlus.setOnClickListener {
            empanada.quantity = empanada.quantity + 1
            binding.tvCounter.text = empanada.quantity.toString()
            onClickListener.onBtnClick( empanada )
        }
    }
}