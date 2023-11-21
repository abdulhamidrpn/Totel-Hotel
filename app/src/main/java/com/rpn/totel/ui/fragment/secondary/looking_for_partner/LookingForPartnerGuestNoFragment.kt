package com.rpn.totel.ui.fragment.secondary.looking_for_partner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentLookingForPartnerGuestNoBinding
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel

class LookingForPartnerGuestNoFragment : BaseFragment() {

    private var _binding: FragmentLookingForPartnerGuestNoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentLookingForPartnerGuestNoBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_lookingForPartnerGuestNoFragment_to_lookingForPartnerInterestsFragment)
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun init() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}