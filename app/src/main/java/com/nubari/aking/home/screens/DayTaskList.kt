package com.nubari.aking.home.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubari.aking.R
import com.nubari.aking.data.model.Task
import com.nubari.aking.databinding.FragmentDayTaskListBinding
import com.nubari.aking.home.adapters.TaskListAdapter
import java.time.LocalDateTime

class DayTaskList : Fragment() {
    private var _binding: FragmentDayTaskListBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDayTaskListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskListAdapter(this)
        val taskListRecyclerView = binding.taskTodayRecyclerView
        val testData = listOf(
            Task(
                title = "test1",
                description = "some desc",
                completed = false,
                dueDate = "test"
            ),
            Task(
                title = "test2",
                description = "some desc",
                completed = true,
                dueDate = "test"
            ),
            Task(
                title = "test3",
                description = "some desc",
                completed = false,
                dueDate = "test"
            ),

        )
        taskListRecyclerView.adapter = adapter
        taskListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.updateTasks(testData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

