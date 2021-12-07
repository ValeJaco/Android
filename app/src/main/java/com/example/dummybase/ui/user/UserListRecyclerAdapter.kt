package com.example.dummybase.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dummybase.R
import com.example.dummybase.data.model.User
import com.example.dummybase.databinding.UserFragmentBinding
import dagger.hilt.android.scopes.FragmentScoped
import androidx.recyclerview.widget.RecyclerView.ViewHolder




@FragmentScoped
class UserListRecyclerAdapter(private val viewModel: UserListViewModel) :
    RecyclerView.Adapter<UserListRecyclerAdapter.UserListHolder>() {

    var items: List<User> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<UserFragmentBinding>(
                inflater,
                R.layout.user_fragment,
                parent,
                false
            )
        return UserListHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListRecyclerAdapter.UserListHolder, position: Int) {
        val item = items[position]
        holder.bind(item,viewModel)
    }

    fun setUserList(userList: List<User>) {
        items = userList
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    class UserListHolder(val binding: UserFragmentBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind( item: User, viewModel: UserListViewModel ) {
            binding.user = item
            binding.vm = viewModel

        }
    }



}