package com.example.empanandascounterkt.adapters.empanadas


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.adapters.empanadas.EmpanadasAdapter
import com.example.empanandascounterkt.databinding.ItemEmpandasBinding
import com.example.empanandascounterkt.models.domainmodels.Empanada

class EmpanadasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemEmpandasBinding.bind(view)


    fun render(empanada: Empanada, position: Int, onClickListener: EmpanadasAdapter.BtnListener){
        binding.tvName.text = empanada.name
        binding.tvCounter.text = empanada.count.toString()

        binding.btnMinus.setOnClickListener {
            empanada.count = empanada.count - 1
            binding.tvCounter.text = empanada.count.toString()
            onClickListener.onBtnClick( empanada,position )
        }

        binding.btnPlus.setOnClickListener {
            empanada.count = empanada.count + 1
            binding.tvCounter.text = empanada.count.toString()
            onClickListener.onBtnClick( empanada, position )
        }
    }
}