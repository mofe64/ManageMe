package com.nubari.aking.presentation.home.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.nubari.aking.R
import com.nubari.aking.data.model.Task
import com.nubari.aking.databinding.TaskTileBinding

class TaskListAdapter(
    private val fragment: Fragment
) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private var tasks: List<Task> = listOf()
    private var viewBinderHelper: ViewBinderHelper = ViewBinderHelper()

    class ViewHolder(view: TaskTileBinding) : RecyclerView.ViewHolder(view.root) {
        val editBtn = view.taskEditBtn
        val deleteBtn = view.taskEditDelete
        val completedCheck = view.taskCompleted
        val swipeRevealLayout: SwipeRevealLayout = view.taskSwipeLayout
        val taskTitle = view.taskTitle
        val taskDueDate = view.taskDueDate
        val card = view.taskContainer
        val coloredBorder = view.colorView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TaskTileBinding = TaskTileBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(holder.swipeRevealLayout, task.id)
        viewBinderHelper.closeLayout(task.id)
        holder.taskTitle.text = task.title
        holder.taskDueDate.text = task.dueDate.toString()
        setTileColor(task, holder)
        holder.completedCheck.setOnClickListener {
            val newCheckedValue = !task.completed
            holder.completedCheck.isChecked = newCheckedValue
            task.completed = newCheckedValue
            setTileColor(task, holder)
        }
        holder.completedCheck.isChecked = task.completed

    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(list: List<Task>) {
        tasks = list
        notifyDataSetChanged()
    }

    private fun setTileColor(task: Task, holder: ViewHolder) {
        if (task.completed) {
            holder.completedCheck.buttonTintList = ContextCompat.getColorStateList(
                fragment.requireContext(),
                R.color.cornflower_blue
            )
            holder.coloredBorder.setBackgroundColor(holder.itemView.resources.getColor(R.color.cornflower_blue))
            holder.taskTitle.paintFlags = holder.taskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.taskDueDate.paintFlags =
                holder.taskDueDate.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.completedCheck.buttonTintList = ContextCompat.getColorStateList(
                fragment.requireContext(),
                R.color.carnation
            )
            holder.taskTitle.paintFlags =
                holder.taskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.taskDueDate.paintFlags =
                holder.taskDueDate.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.coloredBorder.setBackgroundColor(holder.itemView.resources.getColor(R.color.carnation))
        }
    }
}