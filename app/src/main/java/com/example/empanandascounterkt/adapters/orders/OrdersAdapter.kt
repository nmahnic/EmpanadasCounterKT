package com.example.empanandascounterkt.adapters.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.models.domain.Order

class OrdersAdapter(
    private val itemListener: ItemListener
) : RecyclerView.Adapter<OrdersViewHolder>() {

    private var orderList: MutableList<Order> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context )
        return OrdersViewHolder(layoutInflater.inflate(R.layout.item_orders, parent, false))
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = orderList[position]
        holder.render(item, position, itemListener)
    }

    override fun getItemCount(): Int = orderList.size

    fun setList(orderList: MutableList<Order>){
        this.orderList = orderList
    }

    interface ItemListener{
        fun onAddBtnClick(order: Order, position: Int)
        fun onRemoveBtnClick(order: Order, position: Int)
        fun onItemClick(order: Order, position: Int)
    }

}