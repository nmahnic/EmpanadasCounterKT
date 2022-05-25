package com.nicomahnic.empanandascounterkt.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.ui.viewmodels.HomeVM
import com.nicomahnic.empanandascounterkt.adapters.users.UsersAdapter
import com.nicomahnic.empanandascounterkt.databinding.SaveEmpanadaDialogBinding
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import com.nicomahnic.empanandascounterkt.models.domain.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class SaveEmpanadasDialog (
    private val empanadaList: MutableList<Empanada>,
    private val homeVM: HomeVM
): DialogFragment() {

    lateinit var binding: SaveEmpanadaDialogBinding
    lateinit var adapter: UsersAdapter
    private var usersTemp = mutableListOf<User>()

    private var selectedUser: User? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = SaveEmpanadaDialogBinding.inflate(LayoutInflater.from(requireContext()))

        binding.rvUsersDialog.layoutManager = LinearLayoutManager(requireContext())
        adapter  = UsersAdapter(usersTemp, onItemSelected)
        binding.rvUsersDialog.adapter = adapter

        runBlocking {
            homeVM.getAllUsers().collect { users ->
                users.forEach{
                    Log.d("NM", "user => ${it.name} ${it.date}")
                    usersTemp.add(0, it)
                    adapter.notifyItemChanged(0)
                }
            }
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("¿Desea guardar la orden?")
            .setPositiveButton("Aceptar") { dialog, _ ->
                val d = dialog as Dialog
                val comment = d.findViewById<TextInputLayout>(R.id.tvOrderComment)
                comment.editText?.let { comment ->
                    homeVM.insertEmpanadas(
                        empanadaList,
                        comment.text.toString().trim(),
                        selectedUser ?: User(0)
                    )
                    dialog.dismiss()
                }
            }
            .setView(binding.root)
            .create()
    }

    private val onItemSelected = object :  UsersAdapter.ItemListener {
        override fun onBtnClick(user: User) {
            Log.d("NM", "DIALOG user => ${user}")
            selectedUser = user
            usersTemp.forEach { it.selected = (it == user) }
            adapter.notifyDataSetChanged()
        }
    }

}