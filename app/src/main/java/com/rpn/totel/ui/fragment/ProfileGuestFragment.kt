package com.rpn.totel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.rpn.totel.R
import com.rpn.totel.adapters.HomeAdapter
import com.rpn.totel.adapters.secondary.AlreadyBookedAdapter
import com.rpn.totel.adapters.secondary.PartnerAdapter
import com.rpn.totel.adapters.secondary.REVIEWS_TYPE_SMALL
import com.rpn.totel.adapters.secondary.ReviewsAdapter
import com.rpn.totel.animation.ViewAnimation
import com.rpn.totel.databinding.FragmentProfileGuestBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Item
import com.rpn.totel.model.Reviews
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.fragment.extra.AddToWishlistFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel
import com.rpn.totel.utils.Constants

class ProfileGuestFragment : BaseFragment() {

    private var _binding: FragmentProfileGuestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentProfileGuestBinding.inflate(inflater, container, false)
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
        initAlreadyBooked()
        onClick()
    }

    fun initAlreadyBooked() {

        val rvAdapter: AlreadyBookedAdapter? = AlreadyBookedAdapter()
        rvAdapter?.itemClickListener = object : ItemClickListener<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
                findNavController().navigate(R.id.globalActionToDetailsBookedFragment)
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {
                val bottomSheetFragment = AddToWishlistFragment()
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            }
        }
        rvAdapter?.updateDataSet(Constants.demoItemList.shuffled())
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }

    }

    fun initWishlist() {

        val rvAdapter: HomeAdapter? = HomeAdapter()
        rvAdapter?.itemClickListener = object : ItemClickListener<Claims> {
            override fun onItemClick(view: View, position: Int, item: Claims) {
                findNavController().navigate(R.id.globalActionToRoomDetailsFragment)
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Claims) {
                val bottomSheetFragment = AddToWishlistFragment()
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            }
        }
        rvAdapter?.updateDataSet(
            listOf(
                Claims("Author"),
                Claims("Author"),
                Claims("Author"),
                Claims("Author")
            )
        )
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }

    }

    fun initPartner() {

        val rvAdapter: PartnerAdapter? = PartnerAdapter()
        rvAdapter?.itemClickListener = object : ItemClickListener<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
                findNavController().navigate(R.id.globalActionToDetailsLookingForPartnerFragment)
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {
                val bottomSheetFragment = AddToWishlistFragment()
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            }
        }
        rvAdapter?.updateDataSet(Constants.demoProfileList.shuffled())
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }

    fun initReviews() {
        val rvAdapter: ReviewsAdapter? = ReviewsAdapter(REVIEWS_TYPE_SMALL)
        rvAdapter?.updateDataSet(
            listOf(
                Reviews("Author"),
                Reviews("Author"),
                Reviews("Author"),
                Reviews("Author")
            )
        )
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }

    fun onClick() {
        binding.btnShowMoreLess.setOnClickListener {
            if (binding.containerDetails.visibility == View.VISIBLE) {
                ViewAnimation.animateView(binding.containerDetails, false)
                binding.btnShowMoreLess.text = "Show more"
            } else {
                ViewAnimation.animateView(binding.containerDetails, true)
                binding.btnShowMoreLess.text = "Show less"
            }
        }
        binding.containerProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_profileFragment)
        }
        binding.btnAdd.setOnClickListener {
            settingsUtility.isGuestMode = !settingsUtility.isGuestMode
            findNavController().navigate(R.id.navigation_dashboard)
        }


        //Initial view to show when profile is selected
        binding.chipAlreadyBooked.isChecked = true
        binding.chipGroupTags.setOnCheckedStateChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(chipGroup.checkedChipId)?.text
//            Toast.makeText(chipGroup.context, titleOrNull ?: "No Choice", Toast.LENGTH_LONG).show()
            when (chipGroup.checkedChipId) {
                R.id.chip_looking_for_partner -> {
                    binding.list.list.visibility = View.VISIBLE
                    initPartner()
                }

                R.id.chip_already_booked -> {
                    binding.list.list.visibility = View.VISIBLE
                    initAlreadyBooked()
                }

                R.id.chip_reviews -> {
                    binding.list.list.visibility = View.VISIBLE
                    initReviews()
                }

                R.id.chip_wishlist -> {
                    binding.list.list.visibility = View.VISIBLE
                    initWishlist()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}