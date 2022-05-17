package com.example.empanandascounterkt.adapters.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.empanandascounterkt.databinding.ItemUsersBinding
import com.example.empanandascounterkt.models.domain.User

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemUsersBinding.bind(view)


    fun render(user: User, position: Int, onClickListener: UsersAdapter.ItemListener){
        binding.tvName.text = user.name
        binding.tvComment.text = ""

        itemView.setOnClickListener {
            onClickListener.onBtnClick( user ,position )
        }
    }
}