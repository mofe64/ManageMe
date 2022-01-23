package com.nubari.aking.home.adapters

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.nubari.aking.databinding.TaskTileBinding

class TaskListAdapter(
    private val fragment: Fragment
) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(view: TaskTileBinding) : RecyclerView.ViewHolder(view.root) {
        val editBtn = view.taskEditBtn
        val deleteBtn = view.taskEditDelete
        val completedCheck = view.taskCheck
        val swipeRevealLayout: SwipeRevealLayout = view.taskSwipeLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}