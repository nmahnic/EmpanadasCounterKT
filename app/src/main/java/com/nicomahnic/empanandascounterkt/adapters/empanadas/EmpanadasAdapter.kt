package com.nicomahnic.empanandascounterkt.adapters.empanadas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.models.domain.Empanada

class EmpanadasAdapter(
    private var empandasList: MutableList<Empanada>,
    private val btnListener: BtnListener
) : RecyclerView.Adapter<EmpanadasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpanadasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context )
        return EmpanadasViewHolder(layoutInflater.inflate(R.layout.item_empandas, parent, false))
    }

    override fun onBindViewHolder(holder: EmpanadasViewHolder, position: Int) {
        val item = empandasList[position]
        holder.render(item, btnListener)
    }

    override fun getItemCount(): Int = empandasList.size

    interface BtnListener{
        fun onBtnClick(empanada: Empanada)
    }

}