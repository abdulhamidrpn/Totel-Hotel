package com.rpn.totel.ui.fragment.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentLookingForPartnerBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel

class LookingForPartnerFragment : Fragment() {

    private var _binding: FragmentLookingForPartnerBinding? = null

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

        _binding = FragmentLookingForPartnerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }

    fun onClick() {

        binding.btnSendProposal.setOnClickListener {
            findNavController().navigate(R.id.action_lookingForPartnerFragment_to_lookingForPartnerPostFragment)
        }
    }

    fun init() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}