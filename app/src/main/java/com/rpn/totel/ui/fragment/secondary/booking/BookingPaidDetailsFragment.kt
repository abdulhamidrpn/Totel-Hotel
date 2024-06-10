package com.rpn.totel.ui.fragment.secondary.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.rpn.totel.R
import com.rpn.totel.adapters.TagAdapter
import com.rpn.totel.adapters.view_pager.ImageAdapter
import com.rpn.totel.databinding.FragmentBookingPaidDetailsBinding
import com.rpn.totel.extensions.addStatusBarColorUpdate
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Item
import com.rpn.totel.model.TagModel
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Constants

class BookingPaidDetailsFragment : BaseFragment() {

    private var rvAdapter: TagAdapter? = TagAdapter()
    private var _binding: FragmentBookingPaidDetailsBinding? = null

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

        _binding = FragmentBookingPaidDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addStatusBarColorUpdate(R.color.colorBackground)
        init()
        setPopularFacilities()
        onClick()
    }

    fun onClick() {

        binding.btnFindLocation.setOnClickListener {
            findNavController().navigate(R.id.action_bookingPaidDetailsFragment_to_findLocationFragment)
        }
    }

    fun init() {

        val adapter = ImageAdapter()
        adapter.updateDataSet(Constants.demoItemList.shuffled())
        adapter.itemClickListener = object : ItemClickListenerSingle<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {

            }
        }
        binding.viewpager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewpager)
    }

    fun setPopularFacilities() {
        val taglist = listOf(
            TagModel(icon = R.drawable.garden, tagTitle = "Garden View"),
            TagModel(icon = R.drawable.wifi, tagTitle = "Free Wifi"),
            TagModel(icon = R.drawable.washer, tagTitle = "Free Washer"),
            TagModel(icon = R.drawable.air_condition, tagTitle = "Air Conditioning"),
            TagModel(icon = R.drawable.refrigerator, tagTitle = "Refrigerator"),
            TagModel(icon = R.drawable.kitchen, tagTitle = "Kitchen"),
            TagModel(icon = R.drawable.pets, tagTitle = "Pets Allowed"),
            TagModel(icon = R.drawable.dryer, tagTitle = "Dryer"),
            TagModel(icon = R.drawable.video_camera, tagTitle = "Security cameras on property"),
            TagModel(icon = R.drawable.bicycle, tagTitle = "Bicycle")
        )

        rvAdapter?.updateDataSet(taglist)
        binding.list.list.apply {
            val layoutManagerFlexBox = FlexboxLayoutManager(requireContext())
            layoutManagerFlexBox.flexDirection = FlexDirection.ROW
            layoutManagerFlexBox.justifyContent = JustifyContent.FLEX_START
            layoutManager = layoutManagerFlexBox
            adapter = rvAdapter
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}