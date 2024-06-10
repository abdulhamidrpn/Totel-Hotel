package com.rpn.totel.ui.fragment.secondary.looking_for_partner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_LARGE
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_UPLOAD_IMAGE
import com.rpn.totel.adapters.secondary.ThumbnailAdapter
import com.rpn.totel.databinding.FragmentLookingForPartnerUploadImageBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Constants

class LookingForPartnerUploadImageFragment : BaseFragment() {

    private var rvAdapter: ThumbnailAdapter? = ThumbnailAdapter(THUMBNAIL_TYPE_UPLOAD_IMAGE)
    private var _binding: FragmentLookingForPartnerUploadImageBinding? = null

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

        _binding = FragmentLookingForPartnerUploadImageBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_lookingForPartnerUploadImageFragment_to_lookingForPartnerGiveTitleFragment)
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun init() {

        rvAdapter?.updateDataSet(Constants.demoItemList.shuffled())
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}