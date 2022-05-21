package com.nicomahnic.empanandascounterkt.adapters.orders.subEmpanadas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.models.domain.Empanada

class SubEmpanadasAdapter() : RecyclerView.Adapter<SubEmpanadasViewHolder>() {

    private var empanadaList: MutableList<Empanada> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubEmpanadasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context )
        return SubEmpanadasViewHolder(layoutInflater.inflate(R.layout.sub_item_empandas, parent, false))
    }

    override fun onBindViewHolder(holder: SubEmpanadasViewHolder, position: Int) {
        val item = empanadaList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = empanadaList.size

    fun setList(empanadaList: MutableList<Empanada>){
        this.empanadaList = empanadaList
    }

}