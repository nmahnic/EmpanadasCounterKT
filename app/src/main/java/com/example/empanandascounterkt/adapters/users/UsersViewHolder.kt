package com.example.empanandascounterkt.adapters.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.ItemUsersBinding
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.utils.Utils

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemUsersBinding.bind(view)


    fun render(user: User, position: Int, onClickListener: UsersAdapter.ItemListener){
        binding.tvName.text = user.name
        binding.tvDate.text = Utils.parseTimestamp(user.date)
        binding.tvAddress.text = Utils.parseTimestamp(user.date)

        itemView.setOnClickListener {
            onClickListener.onBtnClick( user ,position )
        }
    }
}