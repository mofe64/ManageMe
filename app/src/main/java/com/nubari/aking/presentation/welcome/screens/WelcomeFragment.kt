package com.nubari.aking.presentation.welcome.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.nubari.aking.R
import com.nubari.aking.databinding.FragmentWelcomeBinding
import com.nubari.aking.presentation.welcome.WelcomeViewPagerAdapter
import com.nubari.aking.presentation.welcome.events.WelcomeEvent
import com.nubari.aking.presentation.welcome.viewmodel.WelcomeVieModel


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val viewModel: WelcomeVieModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val slides = listOf<Fragment>(
            WelcomeSlideOne(),
            WelcomeSlideTwo(),
            WelcomeSlideThree()
        )

        val adapter =
            WelcomeViewPagerAdapter(slides, requireActivity().supportFragmentManager, lifecycle)
        binding.welcomeViewPager.adapter = adapter
        binding.welcomeViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.createEvent(WelcomeEvent.SlideChange(position))
                super.onPageSelected(position)
            }
        })
        binding.loginBtn.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            it.findNavController().navigate(action)
        }

        viewModel.welcomeState.observe(viewLifecycleOwner, { welcomeState ->
            when (welcomeState.currentScreen) {
                0 -> {
                    binding.welcomeBanner.setImageResource(R.drawable.bannerred)
                }
                1 -> {
                    binding.welcomeBanner.setImageResource(R.drawable.bannerblue)
                }
                2 -> {
                    binding.welcomeBanner.setImageResource(R.drawable.bannerpurple)
                }
            }
        })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).window.statusBarColor = Color.TRANSPARENT


    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).window.statusBarColor =
            resources.getColor(R.color.carnation)
    }

}