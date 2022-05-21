package com.nicomahnic.empanandascounterkt.adapters.deliveries


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.databinding.ItemDeliveriesBinding
import com.nicomahnic.empanandascounterkt.models.domain.Delivery

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