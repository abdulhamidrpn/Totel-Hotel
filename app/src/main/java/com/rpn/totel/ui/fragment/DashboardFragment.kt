package com.rpn.totel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.animation.ViewAnimation
import com.rpn.totel.databinding.FragmentDashboardBinding
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel

class DashboardFragment : BaseFragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.viewModel = viewModel
            it.executePendingBindings()
            it.lifecycleOwner = this
        }
        onClick()
    }

    fun onClick() {
        binding.containerAccount.setOnClickListener {
            if (binding.containerAccountSubCategory.visibility == View.VISIBLE) {
                ViewAnimation.animateView(binding.containerAccountSubCategory, false)
                ViewAnimation.rotateViewByDegrees(binding.ivAccountDropdown, 180f)
            } else {
                ViewAnimation.animateView(binding.containerAccountSubCategory, true)
                ViewAnimation.rotateViewByDegrees(binding.ivAccountDropdown, 180f)
            }
        }
        binding.btnSwitchToGuest.setOnClickListener {
            settingsUtility.isGuestMode = !settingsUtility.isGuestMode
            findNavController().navigate(R.id.profileGuestFragment)
        }
        binding.containerProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_profileFragment)
        }
        binding.btnBooking.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_bookingFragment)
        }
        binding.btnInbox.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_inboxFragment)
        }
        binding.btnSpace.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_spaceFragment)
        }
        binding.btnBusiness.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_businessFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}