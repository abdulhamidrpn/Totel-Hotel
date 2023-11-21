package com.rpn.totel.ui.fragment.secondary.dashboard.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rpn.totel.databinding.FragmentCalendarBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

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

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.textHome.text = "Calendar"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}