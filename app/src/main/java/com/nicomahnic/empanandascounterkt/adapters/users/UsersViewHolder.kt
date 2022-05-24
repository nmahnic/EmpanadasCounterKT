package com.nicomahnic.empanandascounterkt.adapters.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.empanandascounterkt.databinding.ItemUsersBinding
import com.nicomahnic.empanandascounterkt.models.domain.User
import com.nicomahnic.empanandascounterkt.utils.Utils

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemUsersBinding.bind(view)


    fun render(user: User, position: Int, onClickListener: UsersAdapter.ItemListener){
        binding.tvName.text = user.name
        binding.tvDate.text = Utils.parseTimestamp(user.date)
        binding.tvAddress.text = user.address

        itemView.setOnClickListener {
            onClickListener.onBtnClick( user ,position )
        }
    }
}