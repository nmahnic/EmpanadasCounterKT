package com.nicomahnic.empanandascounterkt.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.ui.viewmodels.UserVM
import com.nicomahnic.empanandascounterkt.adapters.users.UsersAdapter
import com.nicomahnic.empanandascounterkt.databinding.FragmentUserBinding
import com.nicomahnic.empanandascounterkt.models.domain.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var usersTemp = mutableListOf<User>()
    private val userVM: UserVM by viewModels()
    private lateinit var binding : FragmentUserBinding
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserBinding.bind(view)

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener( onFloatingClicked )

    }

    private fun initRecyclerView(){
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter  = UsersAdapter(usersTemp, onItemSelected)
        binding.rvUsers.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch{
            userVM.getAllUsers().collect { users ->
                users.forEach {
                    Log.d("NM", "user => ${it} ")
                    usersTemp.add(0, it)
                    adapter.notifyItemChanged(0)
                }
            }
        }

    }

    private val onItemSelected = object :  UsersAdapter.ItemListener {
        override fun onBtnClick(user: User) {
            Log.d("NM", "user => ${user}")
        }
    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { addNewUserDialog() }
    }


    private fun addNewUserDialog(){
        MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Registre un nuevo usuario")
            .setView(R.layout.user_dialog)
            .setPositiveButton("Aceptar") { dialog, _ ->
                val d = dialog as Dialog
                val userName = d.findViewById<TextInputLayout>(R.id.userName)

                userName.editText?.let { userName ->
                    if(userName.text.toString().isNotEmpty()) {
                        val user = User( name = userName.text.toString().trim(), address = "Aranguren 168")
                        usersTemp.add(user)
                        userVM.insertUser(user)
                        adapter.notifyItemInserted(usersTemp.size - 1)
                    }
                }
                dialog.dismiss()
            }
            .show()
    }


}