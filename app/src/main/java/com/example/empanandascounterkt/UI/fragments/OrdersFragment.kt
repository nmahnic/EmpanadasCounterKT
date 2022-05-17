package com.example.empanandascounterkt.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.UI.viewmodels.OrderVM
import com.example.empanandascounterkt.adapters.orders.OrdersAdapter
import com.example.empanandascounterkt.databinding.FragmentOrdersBinding
import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class OrdersFragment : Fragment(R.layout.fragment_orders) {

    companion object {
        val orderList = mutableListOf(
            Order(User("Nico"), Date(), "comentario 1",
                mutableListOf(
                    Empanada("Carne", 1 ),
                    Empanada("Jamon y Queso", 1 ),
                )
            ),
            Order(User("Luli"), Date(), "comentario 2",
                mutableListOf(
                    Empanada("Carne", 1),
                    Empanada("Verdura", 2),
                )
            )
        )
    }

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private var ordersTemp = mutableListOf<Order>()
    private val orderVM: OrderVM by viewModels()
    private val job = Job()
    private val dispatcherContext = CoroutineScope(Dispatchers.Main + job)
    private lateinit var binding : FragmentOrdersBinding
    private var adapter: OrdersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)
        orderVM.floatingButtonStatus = false
        Thread.sleep(100)

        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        adapter = OrdersAdapter(onItemSelected)
        adapter!!.setList(ordersTemp)
//        adapter!!.setList(orderList)
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

        binding.floatingButton.setOnClickListener(onFloatingClicked)
        binding.floatingEditButton.setOnClickListener(onFloatingEditClicked)
        binding.floatingDeliveryButton.setOnClickListener(onFloatingDeliveryClicked)

    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            setVisibility()
            setAnimation()
            setClickable()
            orderVM.floatingButtonStatus = !orderVM.floatingButtonStatus
        }
    }

    private val onFloatingDeliveryClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { Log.d("NM", "order DELIVERY BUTTON") }
    }

    private val onFloatingEditClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { Log.d("NM", "order EDIT BUTTON") }
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

    private fun setVisibility(){
        if(!orderVM.floatingButtonStatus) {
            binding.floatingEditButton.visibility = View.VISIBLE
            binding.floatingDeliveryButton.visibility = View.VISIBLE
        } else {
            binding.floatingEditButton.visibility = View.GONE
            binding.floatingDeliveryButton.visibility = View.GONE
        }
    }

    private fun setAnimation(){
        if(!orderVM.floatingButtonStatus) {
            binding.floatingEditButton.startAnimation(fromBottom)
            binding.floatingDeliveryButton.startAnimation(fromBottom)
            binding.floatingButton.startAnimation(rotateOpen)
        }else{
            binding.floatingEditButton.startAnimation(toBottom)
            binding.floatingDeliveryButton.startAnimation(toBottom)
            binding.floatingButton.startAnimation(rotateClose)
        }
    }

    private fun setClickable(){
        binding.floatingEditButton.isClickable = !orderVM.floatingButtonStatus
        binding.floatingDeliveryButton.isClickable = !orderVM.floatingButtonStatus
    }

}