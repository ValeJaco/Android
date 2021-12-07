package com.example.dummybase.ui.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.dummybase.R
import com.example.dummybase.data.model.User
import com.example.dummybase.databinding.UserListFragmentBinding
import com.example.dummybase.ui.SwipeGesture
import com.example.dummybase.ui.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.user_list_fragment), LifecycleObserver {

    private val viewModel: UserListViewModel by viewModels()

    // classe binding autogénérée
    private lateinit var binding: UserListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = UserListFragmentBinding.bind(view)

        binding.apply {

            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            userList.adapter = UserListRecyclerAdapter( viewModel )

            val gesture = SwipeGesture()
            // initialisation des ressources nécessaires dans les actions de swipe droite/gauche
            gesture.userAdapter = userList.adapter as UserListRecyclerAdapter
            gesture.baseView = view

            val itemTouchHelper = ItemTouchHelper(gesture)
            itemTouchHelper.attachToRecyclerView( userList );

            viewModel.baseView = view

            swiperefresh.setColorSchemeResources(R.color.design_default_color_error)
            swiperefresh.setOnRefreshListener {
                viewModel.loadUsers()
                viewModel.loadUserDetail(32)
                swiperefresh.isRefreshing = false
            }

            viewModel.loadUserDetail(32)
            viewModel.loadUsers()
        }
    }
}