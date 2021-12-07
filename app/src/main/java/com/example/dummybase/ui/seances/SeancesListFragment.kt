package com.example.dummybase.ui.seances

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.dummybase.R
import com.example.dummybase.databinding.SeancesListFragmentBinding
import com.example.dummybase.ui.SwipeGesture
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context.NOTIFICATION_SERVICE

import android.app.NotificationManager

@AndroidEntryPoint
class SeancesListFragment  : Fragment(R.layout.seances_list_fragment), LifecycleObserver {

    private val viewModel: SeancesListViewModel by viewModels()
    private lateinit var binding: SeancesListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = SeancesListFragmentBinding.bind(view)

        viewModel.notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        binding.apply {

            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            seancesList.adapter = SeancesListRecyclerAdapter( viewModel )

            val gesture = SwipeGesture()
            // initialisation des ressources nécessaires dans les actions de swipe droite/gauche
            gesture.seancesAdapter = seancesList.adapter as SeancesListRecyclerAdapter
            gesture.baseView = view

            val itemTouchHelper = ItemTouchHelper(gesture)
            itemTouchHelper.attachToRecyclerView( seancesList );

            // Initialisation de la vue pour la Snackbar
            viewModel.baseView = view

            swiperefresh.setColorSchemeResources(R.color.design_default_color_error)
            swiperefresh.setOnRefreshListener {
                swiperefresh.isRefreshing = false
                viewModel.loadSeances()
            }

            viewModel.loadSeances()
        }
    }
}