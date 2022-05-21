package com.nicomahnic.empanandascounterkt.adapters.orders

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.adapters.orders.subEmpanadas.SubEmpanadasAdapter
import com.nicomahnic.empanandascounterkt.databinding.ItemOrdersBinding
import com.nicomahnic.empanandascounterkt.models.domain.Order
import com.nicomahnic.empanandascounterkt.utils.Utils

class OrdersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemOrdersBinding.bind(view)


    fun render(order: Order, position: Int, onClickListener: OrdersAdapter.ItemListener, deleteBtnStatus: Boolean){
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

        if(deleteBtnStatus){
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnExpandable.visibility = View.INVISIBLE
        } else{
            binding.btnDelete.visibility = View.INVISIBLE
            binding.btnExpandable.visibility = View.VISIBLE
        }

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

        binding.rbSelected.setOnClickListener {
            order.selected = !order.selected
            binding.rbSelected.isChecked = order.selected
            onClickListener.onSelectedItem( order, position )
        }

        binding.btnDelete.setOnClickListener {
            onClickListener.onDeleteBtnClick( order, position )
        }

        itemView.setOnClickListener {
            onClickListener.onItemClick( order, position )
        }
    }
}