package com.example.dummybase.ui

import android.graphics.Canvas
import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dummybase.ui.user.UserListRecyclerAdapter
import android.R
import android.view.View
import com.example.dummybase.ui.seances.SeancesListRecyclerAdapter
import com.google.android.material.snackbar.Snackbar

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator.Builder


class SwipeGesture() : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    lateinit var userAdapter: UserListRecyclerAdapter
    lateinit var seancesAdapter: SeancesListRecyclerAdapter
    lateinit var baseView: View

    override
    fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        // used for up and down movements
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition

        seancesAdapter.notifyItemChanged(position)
        val seance = seancesAdapter.items[position];
        var dirName = "LEFT"
        if (direction == ItemTouchHelper.RIGHT) {
            dirName = "RIGHT"
        }

        showSnackBar(dirName + " : " + seance.id + " " + seance.name , 2500)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val swipeMaxRangeOfMotion = 400f
        var newDx = dX;
        if (newDx > swipeMaxRangeOfMotion) {
            newDx = swipeMaxRangeOfMotion
        }else if (newDx < - swipeMaxRangeOfMotion) {
            newDx = -swipeMaxRangeOfMotion
        }

        Builder(
            c,
            recyclerView,
            viewHolder,
            newDx,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(Color.RED)
            .addSwipeLeftActionIcon(R.drawable.ic_menu_delete)
            .addSwipeRightBackgroundColor(Color.GREEN)
            .addSwipeRightActionIcon(R.drawable.ic_menu_add)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, newDx , dY, actionState, isCurrentlyActive)
    }

    fun showSnackBar( snackText: String , duration: Int) {
        Snackbar.make( baseView , snackText , duration )
            .show()
    }

}