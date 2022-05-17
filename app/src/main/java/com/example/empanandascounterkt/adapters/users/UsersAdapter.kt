package com.example.empanandascounterkt.adapters.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.models.domain.User

class UsersAdapter(
    private var userList: MutableList<User>,
    private val itemListener: ItemListener
) : RecyclerView.Adapter<UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context )
        return UsersViewHolder(layoutInflater.inflate(R.layout.item_users, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = userList[position]
        holder.render(item, position, itemListener)
    }

    override fun getItemCount(): Int = userList.size

    interface ItemListener{
        fun onBtnClick(user: User, position: Int)
    }

}