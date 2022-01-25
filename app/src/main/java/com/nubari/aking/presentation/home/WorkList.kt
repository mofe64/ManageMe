package com.nubari.aking.presentation.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nubari.aking.R
import com.nubari.aking.databinding.FragmentWorkListBinding
import com.nubari.aking.presentation.home.adapters.WorkListViewPagerAdapter
import com.nubari.aking.presentation.home.screens.DayTaskList
import com.nubari.aking.presentation.home.screens.MonthTaskList


class WorkList : Fragment() {

    private lateinit var binding: FragmentWorkListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWorkListBinding.inflate(
            inflater,
            container,
            false
        )
        val screens = listOf(
            DayTaskList(),
            MonthTaskList()
        )
        val adapter = WorkListViewPagerAdapter(
            screens,
            requireActivity().supportFragmentManager, lifecycle
        )
        val viewPager = binding.workListViewPager
        val tabLayout = binding.tabLayout
        viewPager.adapter = adapter
        // disable view pager swiping because it affects swipe able layout reveal
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Today"
                }
                1 -> {
                    tab.text = "Month"
                }
            }
        }.attach()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}