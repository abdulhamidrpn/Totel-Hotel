package com.rpn.totel

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rpn.totel.animation.ViewAnimation.animateView
import com.rpn.totel.databinding.ActivityMainBinding
import com.rpn.totel.utils.SettingsUtility
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val settingsUtility by inject<SettingsUtility>()

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }

                R.id.navigation_confirmation -> {
                    // Navigate to the profile screen.
                    navController.navigate(R.id.navigation_confirmation)
                    true
                }

                R.id.navigation_notifications -> {
                    // Navigate to the settings screen.
                    navController.navigate(R.id.navigation_notifications)
                    true
                }

                R.id.navigation_dashboard -> {
                    if (settingsUtility.isGuestMode) {
                        navController.navigate(R.id.profileGuestFragment)
                    } else {
                        navController.navigate(R.id.navigation_dashboard)
                    }
                    true
                }

                else -> false
            }
        }


        navController.addOnDestinationChangedListener { _, destination, arguments ->

            binding.ivBack.visibility = View.VISIBLE
            binding.ivLogo.visibility = View.INVISIBLE

            when (destination.id) {
                R.id.navigation_home -> {
                    showBottomBar()
                    toolbarAsHome("Totel")
                }

                R.id.navigation_confirmation -> {
                    showBottomBar()
                    toolbarAsHome("Confirmation")
                }

                R.id.navigation_notifications -> {
                    showBottomBar()
                    toolbarAsHome("Notifications")
                }

                R.id.navigation_dashboard -> {
                    showBottomBar()
                    disableLayoutBehaviour()
                }

                R.id.profileGuestFragment -> {
                    showBottomBar()
                    disableLayoutBehaviour()
                }

                R.id.inboxFragment -> {
                    hideBottomBar()
//                    showBoldToolbar("Inbox")
                }

                R.id.bookingFragment -> {
                    hideBottomBar()
//                    showBoldToolbar("Bookings")
                }

                R.id.spaceFragment -> {
                    hideBottomBar()
//                    showBoldToolbar("Space")
                }

                R.id.businessFragment -> {
                    hideBottomBar()
//                    showBoldToolbar("Business")
                }

                else -> {
                    hideBottomBar()
//                    showToolbar()
                }
            }
        }
    }


    private fun hideBottomBar() {
        runOnUiThread {
            if (binding.navView.visibility == View.VISIBLE) {
                animateView(binding.navView, false)
                disableLayoutBehaviour()
            }
        }
    }

    private fun showBottomBar() {
        runOnUiThread {
            if (binding.navView.visibility != View.VISIBLE) {
                animateView(binding.navView, true)
                binding.navView.visibility = View.VISIBLE
            }

        }
    }

    private fun enableLayoutBehaviour() {
        val toolbar = binding.toolbar

        // Check if the toolbar is visible.
        if (toolbar.visibility != View.VISIBLE) {
            toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_up))
            toolbar.visibility = View.VISIBLE
        }
    }

    private fun disableLayoutBehaviour() {


        val toolbar = binding.toolbar

        // Check if the toolbar is visible.
        if (toolbar.visibility == View.VISIBLE) {
            toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_up))
            toolbar.visibility = View.GONE
        }
    }

    private fun toolbarAsHome(title: String) {

        enableLayoutBehaviour()
        binding.ivMenu.visibility = View.VISIBLE
        binding.ivBack.visibility = View.INVISIBLE
        binding.ivLogo.visibility = View.VISIBLE

        if (title != null) {

//            binding.toolbarTitle.setTypeface(null, Typeface.NORMAL)
//            binding.toolbarTitle.textSize = resources.getDimension(com.intuit.ssp.R.dimen._8ssp)
            binding.toolbarTitle.text = title
        }
    }

    private fun showToolbar(title: String? = "") {
        binding.ivMenu.visibility = View.GONE
        if (title != null) {

            binding.toolbarTitle.setTypeface(null, Typeface.NORMAL)
            binding.toolbarTitle.textSize = resources.getDimension(com.intuit.ssp.R.dimen._8ssp)
            binding.toolbarTitle.text = title
        }

    }

    private fun showBoldToolbar(title: String? = "") {
        binding.ivMenu.visibility = View.GONE
        if (title != null) {
            binding.toolbarTitle.setTypeface(null, Typeface.BOLD)
            binding.toolbarTitle.textSize = resources.getDimension(com.intuit.ssp.R.dimen._10ssp)
            binding.toolbarTitle.text = title
        }

    }


    fun onBackButtonClicked(view: View) {
        onBackPressed()
        println("onBackPressed called ${navController.currentDestination?.displayName}")
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(R.string.app_name)
        builder.setIcon(R.drawable.company_logo)
        builder.setMessage("Do you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { _, _ -> finish() }
            .setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.cancel() }
        val alert = builder.create()

        if (navController.currentDestination?.id == R.id.navigation_home) {
            alert.show()
        } else {
            val selectedItem = binding.navView.selectedItemId
            val homeItemId = R.id.navigation_home

            if (selectedItem != homeItemId && binding.navView.visibility == View.VISIBLE) {
                // If not on the home page, navigate to the home page
                binding.navView.selectedItemId = homeItemId
            } else {
                super.onBackPressed()
            }
        }
    }
}