package com.nicomahnic.empanandascounterkt.UI.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.UI.viewmodels.UserVM
import com.nicomahnic.empanandascounterkt.adapters.users.UsersAdapter
import com.nicomahnic.empanandascounterkt.databinding.FragmentUserBinding
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import com.nicomahnic.empanandascounterkt.models.domain.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    companion object {
        var usersTemp = mutableListOf<User>()
    }

    private val userVM: UserVM by viewModels()
    private val job = Job()
    private val dispatcherContext = CoroutineScope(Dispatchers.Main + job)
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

        dispatcherContext.launch{
            dispatcherContext.async{
                userVM.getAllUsers().collect { users ->
                    users.forEach{
                        Log.d("NM", "user => ${it.name} ${it.date}")
                        usersTemp.add(0, it)
                        adapter.notifyItemChanged(0)
                    }
                }
            }.await()
        }
    }

    private val onItemSelected = object :  UsersAdapter.ItemListener {
        override fun onBtnClick(user: User, position: Int) {
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
                        usersTemp.add(User(userName.text.toString().trim(), ))
                        adapter.notifyItemInserted(usersTemp.size - 1)
                    }
                }
                dialog.dismiss()
            }
            .show()
    }


}