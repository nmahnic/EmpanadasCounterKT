package com.example.empanandascounterkt.UI.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.adapters.users.UsersAdapter
import com.example.empanandascounterkt.databinding.FragmentUserBinding
import com.example.empanandascounterkt.models.domainmodels.User

class UserFragment : Fragment(R.layout.fragment_user) {

    companion object {
        val userList = mutableListOf(
            User("Mash1", "lo de siempre"),
            User("Luli","lo que pide lu"),
        )
    }
    
    private lateinit var binding : FragmentUserBinding
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserBinding.bind(view)

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener( onFloatingClicked )

    }

    private fun initRecyclerView(){
        val recyclerView = binding.rvUsers
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter  = UsersAdapter(userList, onItemSelected)
        recyclerView.adapter = adapter
    }

    private val onItemSelected = object :  UsersAdapter.ItemListener {
        override fun onBtnClick(user: User, position: Int) {
            Log.d("NM", "user => ${user}")
        }
    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            Log.d("NM", "user => onFloatingClicked")
        }
    }


}