package com.rpn.totel.interfaces

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.adapters.secondary.ReviewsAdapter

class SwipeHelperCallback(private val recyclerView: RecyclerView) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return ItemTouchHelper.Callback.makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        targetViewHolder: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val adapter = recyclerView.adapter as ReviewsAdapter

        // Get the full view for the swiped item.
        val fullView = adapter.getFullView(position)

        // Add the full view to the RecyclerView as a child view.
        recyclerView.addView(fullView)

        // Set the visibility of the full view to VISIBLE.
        fullView.visibility = View.VISIBLE

        // Animate the full view into place.
        fullView.animate().translationX(0f).setDuration(200L).start()
    }
}
