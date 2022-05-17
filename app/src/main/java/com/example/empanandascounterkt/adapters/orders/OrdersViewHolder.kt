package com.example.empanandascounterkt.adapters.orders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.ItemOrdersBinding
import com.example.empanandascounterkt.databinding.ItemUsersBinding
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.utils.Utils

class OrdersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemOrdersBinding.bind(view)


    fun render(order: Order, position: Int, onClickListener: OrdersAdapter.ItemListener){
        binding.tvName.text = if(order.user.name.isNotBlank()) order.user.name else "Generica"
        binding.tvDate.text = Utils.parseTimestamp(order.date)

        binding.tvComment.text = order.comment
        binding.tvComment.visibility = if(order.comment.isNotBlank()) View.VISIBLE else View.GONE

        binding.btnAdd.setOnClickListener {
            onClickListener.onAddBtnClick( order, position )
            binding.btnAdd.visibility = View.INVISIBLE
            binding.btnRemove.visibility = View.VISIBLE
        }

        binding.btnRemove.setOnClickListener {
            onClickListener.onRemoveBtnClick( order, position )
            binding.btnRemove.visibility = View.INVISIBLE
            binding.btnAdd.visibility = View.VISIBLE
        }

        itemView.setOnClickListener {
            onClickListener.onItemClick( order, position )
        }
    }
}