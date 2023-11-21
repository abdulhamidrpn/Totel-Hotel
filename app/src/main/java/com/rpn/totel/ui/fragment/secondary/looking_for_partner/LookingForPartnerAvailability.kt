package com.rpn.totel.ui.fragment.secondary.looking_for_partner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_UPLOAD_IMAGE
import com.rpn.totel.adapters.secondary.ThumbnailAdapter
import com.rpn.totel.databinding.FragmentLookingForPartnerAvailablityBinding
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.fragment.extra.CalendarFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel


class LookingForPartnerAvailability : BaseFragment() {
    private var _binding: FragmentLookingForPartnerAvailablityBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentLookingForPartnerAvailablityBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        onClick()
    }

    fun onClick() {

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_lookingForPartnerAvailability_to_refundPaymentFragment)
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvCheckIn.setOnClickListener {
            val bottomSheetFragment = CalendarFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

    fun init() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}