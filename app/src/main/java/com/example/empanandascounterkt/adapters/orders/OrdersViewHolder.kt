package com.example.empanandascounterkt.adapters.orders

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.adapters.orders.subEmpanadas.SubEmpanadasAdapter
import com.example.empanandascounterkt.databinding.ItemOrdersBinding
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.utils.Utils

class OrdersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemOrdersBinding.bind(view)


    fun render(order: Order, position: Int, onClickListener: OrdersAdapter.ItemListener){
        var adapter : SubEmpanadasAdapter? = null
        var btnExpandableStatus = false

        binding.tvName.text = if(order.user.name.isNotBlank()) order.user.name else "Generica"
        binding.tvDate.text = Utils.parseTimestamp(order.date)

        binding.tvComment.text = order.comment
        binding.tvComment.visibility = if(order.comment.isNotBlank()) View.VISIBLE else View.GONE

        binding.rvSubEmpanadas.layoutManager = LinearLayoutManager(binding.rvSubEmpanadas.context)
        adapter = SubEmpanadasAdapter()
        adapter.setList(order.empanadaList)
        binding.rvSubEmpanadas.adapter = adapter

        binding.btnExpandable.setOnClickListener {
            if(!btnExpandableStatus){
                binding.rvSubEmpanadas.visibility = View.VISIBLE
                binding.btnExpandable.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                btnExpandableStatus = true
            } else {
                binding.rvSubEmpanadas.visibility = View.GONE
                binding.btnExpandable.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                btnExpandableStatus = false
            }
        }

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