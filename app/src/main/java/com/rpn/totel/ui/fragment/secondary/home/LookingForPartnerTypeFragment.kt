package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentHomeLookingForPartnerTypeBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel

class LookingForPartnerTypeFragment : Fragment() {

    private var _binding: FragmentHomeLookingForPartnerTypeBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeLookingForPartnerTypeBinding.inflate(inflater, container, false)
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

        binding.containerEveryone.setOnClickListener {
            findNavController().navigate(R.id.action_lookingForPartnerTypeFragment_to_selectDestinationFragment)
        }
        binding.containerMen.setOnClickListener {
            findNavController().navigate(R.id.action_lookingForPartnerTypeFragment_to_selectDestinationFragment)
        }
        binding.containerWomen.setOnClickListener {
            findNavController().navigate(R.id.action_lookingForPartnerTypeFragment_to_selectDestinationFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}