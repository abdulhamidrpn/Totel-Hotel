package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.ItemAdapter
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_LARGE
import com.rpn.totel.adapters.secondary.ThumbnailAdapter
import com.rpn.totel.databinding.FragmentHomeRoomDetailsBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Item
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Constants

class RoomDetailsFragment : Fragment() {
    private var rvAdapterYouMayLike: ThumbnailAdapter? = ThumbnailAdapter(THUMBNAIL_TYPE_LARGE)
    private var rvAdapterAmenities: ItemAdapter? = ItemAdapter()
    private var _binding: FragmentHomeRoomDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeRoomDetailsBinding.inflate(inflater, container, false)
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
        init()
    }

    fun onClick() {

//        binding.btnBooking.setOnClickListener {
//
//        }
    }

    fun init() {

        rvAdapterYouMayLike?.updateDataSet(Constants.demoItemList.shuffled())
        binding.listRoom.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterYouMayLike
        }

        rvAdapterAmenities?.updateDataSet(
            listOf(
                Item(R.drawable.garden, "Garden View"),
                Item(R.drawable.wifi, "Free Wifi"),
                Item(R.drawable.washer, "Free Washer"),
                Item(R.drawable.air_condition, "Air Conditioning"),
                Item(R.drawable.refrigerator, "Refrigerator"),
                Item(R.drawable.kitchen, "Kitchen"),
                Item(R.drawable.pets, "Pets Allowed"),
                Item(R.drawable.dryer, "Dryer"),
                Item(R.drawable.video_camera, "Security cameras on property"),
                Item(R.drawable.bicycle, "Bicycle")
            )
        )
        binding.listAmenities.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterAmenities
        }
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.globalActionToRoomBookingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}