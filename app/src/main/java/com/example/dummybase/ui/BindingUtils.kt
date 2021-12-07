package com.example.dummybase.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dummybase.data.model.Seance
import com.example.dummybase.data.model.User
import com.example.dummybase.ui.seances.SeancesListRecyclerAdapter
import com.example.dummybase.ui.user.UserListRecyclerAdapter

@BindingAdapter("show")
fun View.setVisibility(show: Boolean) {
    visibility = if (show)
        View.VISIBLE
    else
        View.INVISIBLE
}

@BindingAdapter("userListAdapter")
fun RecyclerView.setUserListAdapter(userList: List<User>?) {
    (adapter as? UserListRecyclerAdapter)?.setUserList(userList ?: listOf())
}

@BindingAdapter("seancesListAdapter")
fun RecyclerView.setSeanceListAdapter(seancesList: List<Seance>?) {
    (adapter as? SeancesListRecyclerAdapter)?.setSeancesList(seancesList ?: listOf())
}