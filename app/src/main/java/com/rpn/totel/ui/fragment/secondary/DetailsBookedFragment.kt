package com.rpn.totel.ui.fragment.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.adapters.view_pager.ImageAdapter
import com.rpn.totel.databinding.FragmentDetailsBookedHotelBinding
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Item
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Constants

class DetailsBookedFragment : Fragment() {

    private var _binding: FragmentDetailsBookedHotelBinding? = null

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

        _binding = FragmentDetailsBookedHotelBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }

    fun onClick() {

        binding.btnBookNow.setOnClickListener {
            findNavController().navigate(R.id.action_detailsBookedFragment_to_paymentFragment)
        }
    }

    fun init() {

        val adapter = ImageAdapter()
        adapter.updateDataSet(Constants.demoItemList)
        adapter.itemClickListener = object : ItemClickListenerSingle<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {

            }
        }
        binding.viewpager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewpager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}