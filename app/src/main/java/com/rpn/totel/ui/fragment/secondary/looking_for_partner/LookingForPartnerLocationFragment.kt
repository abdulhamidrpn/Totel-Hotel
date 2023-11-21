package com.rpn.totel.ui.fragment.secondary.looking_for_partner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentLookingForPartnerLocationBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel

class LookingForPartnerLocationFragment : Fragment() {

    private var _binding: FragmentLookingForPartnerLocationBinding? = null

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

        _binding = FragmentLookingForPartnerLocationBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_lookingForPartnerLocationFragment_to_lookingForPartnerGuestNoFragment)
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