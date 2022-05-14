package com.example.empanandascounterkt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.empanandascounterkt.databinding.FragmentUserBinding

class UserFragment : Fragment(R.layout.fragment_user) {

    lateinit var binding : FragmentUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserBinding.bind(view)
    }


}