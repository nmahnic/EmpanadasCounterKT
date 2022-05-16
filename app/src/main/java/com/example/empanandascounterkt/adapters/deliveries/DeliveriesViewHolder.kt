package com.example.empanandascounterkt.adapters.deliveries


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.adapters.empanadas.EmpanadasAdapter
import com.example.empanandascounterkt.databinding.ItemDeliveriesBinding
import com.example.empanandascounterkt.databinding.ItemEmpandasBinding
import com.example.empanandascounterkt.models.domainmodels.Delivery

class DeliveriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemDeliveriesBinding.bind(view)


    fun render(delivery: Delivery, position: Int, onClickListener: DeliveriesAdapter.ItemListener){
        binding.tvName.text = delivery.name
        binding.tvAddress.text = delivery.address

        itemView.setOnClickListener {
            onClickListener.onBtnClick( delivery ,position )
        }
    }
}