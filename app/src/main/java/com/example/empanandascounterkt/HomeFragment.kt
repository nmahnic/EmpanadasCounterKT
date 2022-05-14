package com.example.empanandascounterkt

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empanandascounterkt.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        val empanadaList = mutableListOf(
            Empanada("Carne", 1, ""),
            Empanada("Jamon y queso", 1, ""),
        )
    }

    val homeVM: HomeVM by viewModels()
    lateinit var binding : FragmentHomeBinding
    lateinit var adapter: EmpanadasAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener(onFloatingCliked)
    }

    private fun initRecyclerView(){
        val recyclerView = binding.rvEmpanadas
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter  = EmpanadasAdapter(empanadaList, onItemSelected)
        recyclerView.adapter = adapter
    }

    private val onItemSelected = object :  EmpanadasAdapter.BtnListener {
        override fun onBtnClick(empanada: Empanada, position: Int) {
            Log.d("NM", "empanada => ${empanada}")
            if(empanada.count <= 0) {
                empanadaList.remove(empanada)
                adapter.notifyItemRemoved(position)
            }
        }
    }

    private val onFloatingCliked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("¿Qué empanada desea?")
                .setView(R.layout.input_dialog)
                .setPositiveButton("Aceptar") { dialog, _ ->
                    val d = dialog as Dialog
                    val empanadaName = d.findViewById<TextInputLayout>(R.id.empanadaName)
                    empanadaName.editText?.let { empanadaName ->
                        if(empanadaName.text.toString().isNotEmpty()) {
                            empanadaList.add(Empanada(empanadaName.text.toString(), 1, ""))
                            adapter.notifyItemInserted(empanadaList.size - 1)
                        }
                    }
                    dialog.dismiss()
                }
                .show()
        }
    }

}