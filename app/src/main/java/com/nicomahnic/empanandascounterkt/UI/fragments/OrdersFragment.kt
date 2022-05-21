package com.nicomahnic.empanandascounterkt.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.UI.viewmodels.OrderVM
import com.nicomahnic.empanandascounterkt.adapters.orders.OrdersAdapter
import com.nicomahnic.empanandascounterkt.databinding.FragmentOrdersBinding
import com.nicomahnic.empanandascounterkt.models.domain.Order
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private var ordersTemp = mutableListOf<Order>()
    private var selectedOrdersTemp = mutableListOf<Order>()
    private val orderVM: OrderVM by viewModels()
    private val job = Job()
    private val dispatcherContext = CoroutineScope(Dispatchers.Main + job)
    private lateinit var binding : FragmentOrdersBinding
    private lateinit var adapter: OrdersAdapter
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        v = view
        binding = FragmentOrdersBinding.bind(view)
        orderVM.floatingButtonStatus = false
        Thread.sleep(100)

        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        adapter = OrdersAdapter(onItemSelected)
        adapter.setList(ordersTemp)
        binding.rvOrders.adapter = adapter

        dispatcherContext.launch{
            dispatcherContext.async{
                orderVM.getAllOrders().collect { orders ->
                    orders.forEach{
                        Log.d("NM", "order => ${it.user} ${it.date}")
                        ordersTemp.add(0, it)
                        adapter.notifyItemChanged(0)
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
        override fun onClick(p0: View?) {
            Log.d("NM", "order DELIVERY BUTTON")
            if(selectedOrdersTemp.isNotEmpty()){
                val order = selectedOrdersTemp[0]
                Log.d("NM", "order DELIVERY => ${order}")
                val action = OrdersFragmentDirections.actionOrdersFragmentToDeliveryFragment(order)
                v.findNavController().navigate(action)
            }
        }
    }

    private val onFloatingEditClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            Log.d("NM", "order EDIT BUTTON")
            adapter.toggleDeleteButton()
            adapter.notifyDataSetChanged()
        }
    }

    private val onItemSelected = object :  OrdersAdapter.ItemListener {
        override fun onSelectedItem(order: Order, position: Int) {
            Log.d("NM", "order ADD=> ${order.selected} ${order.date}")
            if(order.selected){ selectedOrdersTemp.add(order) }
            else{ selectedOrdersTemp.remove(order) }
            Log.d("NM", "selectedOrdersTemp => ${selectedOrdersTemp}")
        }

        override fun onDeleteBtnClick(order: Order, position: Int) {
            Log.d("NM", "order DELETE => ${order.user} ${order.date}")
            orderVM.deleteOrder(order.id)
            val index = ordersTemp.indexOf(order)
            ordersTemp.remove(order)
            adapter.notifyItemRemoved(index)
        }

        override fun onItemClick(order: Order, position: Int) {
            Log.d("NM", "order ITEM => ${order.user} ${order.date}")
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