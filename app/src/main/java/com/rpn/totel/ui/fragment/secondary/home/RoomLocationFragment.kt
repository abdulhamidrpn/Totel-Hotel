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
import com.rpn.totel.databinding.FragmentHomeRoomLocationBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Item
import com.rpn.totel.ui.viewmodel.HomeViewModel

class RoomLocationFragment : Fragment() {
    private var _binding: FragmentHomeRoomLocationBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeRoomLocationBinding.inflate(inflater, container, false)
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
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.globalActionToRoomReviewsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}