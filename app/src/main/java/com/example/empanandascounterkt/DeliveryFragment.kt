package com.example.empanandascounterkt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.empanandascounterkt.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment(R.layout.fragment_delivery) {

    private lateinit var binding : FragmentDeliveryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeliveryBinding.bind(view)
    }
}