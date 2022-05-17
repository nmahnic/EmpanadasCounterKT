package com.example.empanandascounterkt.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.UI.viewmodels.OrderVM
import com.example.empanandascounterkt.adapters.orders.OrdersAdapter
import com.example.empanandascounterkt.databinding.FragmentOrdersBinding
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private var ordersTemp = mutableListOf<Order>()
    private val orderVM: OrderVM by viewModels()
    private val job = Job()
    private val dispatcherContext = CoroutineScope(Dispatchers.Main + job)
    private lateinit var binding : FragmentOrdersBinding
    private var adapter: OrdersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)
        orderVM.variable = true
        Thread.sleep(100)

        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        adapter = OrdersAdapter(onItemSelected)
        adapter!!.setList(ordersTemp)
        binding.rvOrders.adapter = adapter

        dispatcherContext.launch{
            dispatcherContext.async{
                orderVM.getAllOrders().collect { orders ->
                    orders.forEach{
                        Log.d("NM", "order => ${it.user} ${it.date}")
                        ordersTemp.add(0, it)
                        adapter!!.notifyItemChanged(0)
                    }
                }
            }.await()
        }

    }


    private val onItemSelected = object :  OrdersAdapter.ItemListener {
        override fun onAddBtnClick(order: Order, position: Int) {
            Log.d("NM", "order ADD=> ${order.user} ${order.date}")
        }

        override fun onRemoveBtnClick(order: Order, position: Int) {
            Log.d("NM", "order REMOVE=> ${order.user} ${order.date}")
        }

        override fun onItemClick(order: Order, position: Int) {
            Log.d("NM", "order ITEM=> ${order.user} ${order.date}")
        }
    }

}