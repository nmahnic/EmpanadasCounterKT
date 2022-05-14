package com.example.empanandascounterkt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        holder.render(item, position, btnListener)
    }

    override fun getItemCount(): Int = empandasList.size

    fun setList(empandasList: List<Empanada>){
        this.empandasList = empandasList as MutableList<Empanada>
    }

    interface BtnListener{
        fun onBtnClick(empanada: Empanada, position: Int)
    }

}