package com.nubari.aking.presentation.home.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nubari.aking.databinding.FragmentDayTaskListBinding
import com.nubari.aking.presentation.home.adapters.TaskListAdapter
import com.nubari.aking.presentation.home.viewmodels.DayTaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DayTaskList : Fragment() {
    private var _binding: FragmentDayTaskListBinding? = null
    private val viewModel by viewModels<DayTaskViewModel>()

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
        taskListRecyclerView.adapter = adapter
        taskListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //set up observables
        viewModel.state.observe(viewLifecycleOwner) { state ->
            state.tasks.let {
                if (it.isEmpty()) {
                    Log.i("day", "is empty")
                    Log.i("day task", it.toString())
                    binding.taskTodayRecyclerView.visibility = View.GONE
                    binding.emptyTaskText.visibility = View.VISIBLE
                } else {
                    Log.i("day task", it.toString())
                    binding.taskTodayRecyclerView.visibility = View.VISIBLE
                    binding.emptyTaskText.visibility = View.GONE
                    adapter.updateTasks(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

