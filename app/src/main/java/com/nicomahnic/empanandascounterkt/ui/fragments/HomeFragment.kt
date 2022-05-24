package com.nicomahnic.empanandascounterkt.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.ui.viewmodels.HomeVM
import com.nicomahnic.empanandascounterkt.adapters.empanadas.EmpanadasAdapter
import com.nicomahnic.empanandascounterkt.databinding.FragmentHomeBinding
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        val empanadaList = mutableListOf(
            Empanada("Carne", 1, ),
            Empanada("Jamon y queso", 1, ),
        )
    }

    private val rotateOpen: Animation by lazy { loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private val homeVM: HomeVM by viewModels()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter: EmpanadasAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        initRecyclerView()

        binding.floatingButton.setOnClickListener(onFloatingClicked)
        binding.floatingAddButton.setOnClickListener(onFloatingAddClicked)
        binding.floatingSaveButton.setOnClickListener(onFloatingSaveClicked)
    }

    private fun initRecyclerView(){
        val recyclerView = binding.rvEmpanadas
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter  = EmpanadasAdapter(empanadaList, onItemSelected)
        recyclerView.adapter = adapter
    }

    private val onItemSelected = object :  EmpanadasAdapter.BtnListener {
        override fun onBtnClick(empanada: Empanada) {
            Log.d("NM", "empanada => ${empanada}")
            Log.d("NM", "empanadaList => ${empanadaList}")
            if(empanada.quantity <= 0) {
                val pos = empanadaList.indexOf(empanada)
                empanadaList.remove(empanada)
                adapter.notifyItemRemoved(pos)
            }
        }
    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            setVisibility()
            setAnimation()
            setClickable()
            homeVM.floatingButtonStatus = !homeVM.floatingButtonStatus
        }
    }

    private val onFloatingSaveClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { saveEmpanadasDialog() }
    }

    private val onFloatingAddClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { addNewEmpanadaDialog() }
    }

    private fun addNewEmpanadaDialog(){
        MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("¿Qué empanada desea?")
            .setView(R.layout.empanada_dialog)
            .setPositiveButton("Aceptar") { dialog, _ ->
                val d = dialog as Dialog
                val empanadaName = d.findViewById<TextInputLayout>(R.id.empanadaName)
                empanadaName.editText?.let { empanadaName ->
                    if(empanadaName.text.toString().isNotEmpty()) {
                        empanadaList.add(Empanada(empanadaName.text.toString().trim(), 1))
                        adapter.notifyItemInserted(empanadaList.size - 1)
                    }
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun saveEmpanadasDialog(){
        val dialog = SaveEmpanadasDialogFragment(empanadaList, homeVM)
        val fm: FragmentManager = requireActivity().supportFragmentManager
        dialog.show(fm,"custom")
    }

    private fun setVisibility(){
        if(!homeVM.floatingButtonStatus) {
            binding.floatingAddButton.visibility = View.VISIBLE
            binding.floatingSaveButton.visibility = View.VISIBLE
        } else {
            binding.floatingAddButton.visibility = View.GONE
            binding.floatingSaveButton.visibility = View.GONE
        }
    }

    private fun setAnimation(){
        if(!homeVM.floatingButtonStatus) {
            binding.floatingAddButton.startAnimation(fromBottom)
            binding.floatingSaveButton.startAnimation(fromBottom)
            binding.floatingButton.startAnimation(rotateOpen)
        }else{
            binding.floatingAddButton.startAnimation(toBottom)
            binding.floatingSaveButton.startAnimation(toBottom)
            binding.floatingButton.startAnimation(rotateClose)
        }
    }

    private fun setClickable(){
        binding.floatingAddButton.isClickable = !homeVM.floatingButtonStatus
        binding.floatingSaveButton.isClickable = !homeVM.floatingButtonStatus
    }
}