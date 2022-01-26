package com.nubari.aking.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.View
import androidx.navigation.ui.setupWithNavController
import com.nubari.aking.R
import com.nubari.aking.databinding.ActivityMainBinding
import com.nubari.aking.presentation.home.dialogs.PrimaryActionDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        val toolbarCustomTitle = binding.toolbarTitle
        setSupportActionBar(toolbar)
        toolbarCustomTitle.text = toolbar.title
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val bottomNavBar = binding.bottomNavBar
        //fix for bottom nav bar shadow in bottom app bar
        bottomNavBar.background = null
        // disable placeholder item
        bottomNavBar.menu.getItem(2).isEnabled = false
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.profile,
                R.id.menuView,
                R.id.workList,
                R.id.quickView
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavBar.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            val screensToHideNavBarOn = listOf(
                R.id.welcomeFragment,
                R.id.loginFragment
            )
            toolbarCustomTitle.text = destination.label
            if (screensToHideNavBarOn.contains(destination.id)) {
                binding.bottomAppBar.visibility = View.GONE
                binding.fab.visibility = View.GONE
                bottomNavBar.visibility = View.GONE
            } else {
                binding.bottomAppBar.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
                bottomNavBar.visibility = View.VISIBLE
            }
        }
        binding.fab.setOnClickListener {
           showPrimaryDialog()
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun showPrimaryDialog() {
        val dialog: PrimaryActionDialog = PrimaryActionDialog.newInstance()
        dialog.show(supportFragmentManager, "Primary Action Dialog")
    }

}