package com.example.empanandascounterkt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.empanandascounterkt.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment(R.layout.fragment_orders) {

    lateinit var binding: FragmentOrdersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)

    }
}