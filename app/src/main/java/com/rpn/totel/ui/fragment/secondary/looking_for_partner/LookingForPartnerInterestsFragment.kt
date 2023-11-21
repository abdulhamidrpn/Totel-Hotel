package com.rpn.totel.ui.fragment.secondary.looking_for_partner

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentLookingForPartnerIntersestsBinding
import com.rpn.totel.model.TagModel
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel

class LookingForPartnerInterestsFragment : BaseFragment() {

    private var _binding: FragmentLookingForPartnerIntersestsBinding? = null

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

        _binding = FragmentLookingForPartnerIntersestsBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_lookingForPartnerInterestsFragment_to_lookingForPartnerUploadImageFragment)
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun init() {
        val taglist = listOf(
            TagModel("Travel", true),
            TagModel("Art"),
            TagModel("Sports"),
            TagModel("Swimming", true),
            TagModel("Playing Games"),
            TagModel("Gym"),
            TagModel("Learning Languages"),
            TagModel("Photography"),
            TagModel("Cooking"),
            TagModel("Coding", true),
            TagModel("Making new things")
        )
        launch {
            taglist.forEach {
                setCategoryChip(it)
            }
        }

    }
    fun setCategoryChip(category: TagModel) {
        val mChip = this.layoutInflater.inflate(R.layout.item_chip_tag, null, false) as Chip
        mChip.text = category.tagTitle
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f,
            resources.displayMetrics
        ).toInt()
        mChip.setPadding(paddingDp, 0, paddingDp, 0)

        if (category.isSelected == true) {
            mChip.isSelected = true
        }

        mChip.setOnCheckedChangeListener { compoundButton, b ->
            Toast.makeText(requireContext(), "${compoundButton.text}", Toast.LENGTH_SHORT).show()
        }
        binding.chipGroupTags.addView(mChip)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}