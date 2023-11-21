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
import com.rpn.totel.databinding.FragmentHomeBinding
import com.rpn.totel.extensions.delayOnLifecycle
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.ui.fragment.base.BaseFragment
import com.rpn.totel.ui.fragment.extra.AddCardFragment
import com.rpn.totel.ui.fragment.extra.AddToWishlistFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    private var rvAdapter: HomeAdapter? = HomeAdapter()

    private var _binding: FragmentHomeBinding? = null

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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }

    fun onClick() {
        binding.btnDemo.setOnClickListener {
            // TODO: Organize demo fragments

//            val bottomSheetFragment = AddNewCardFragment()
//            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)

            findNavController().navigate(R.id.globalActionToChatFragment)

        }
    }


    private fun showBottomSheet() {
        val bottomSheetFragment = AddCardFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)

        // Listen for layout changes to handle the keyboard
        /* bottomSheetFragment.view?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
             override fun onGlobalLayout() {
                 val rootView = findViewById<View>(android.R.id.content)
                 val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                 if (imm.isAcceptingText && rootView != null) {
                     // Adjust the height of the Bottom Sheet to accommodate the keyboard
                     val heightDiff = rootView.height - bottomSheetFragment.view?.height ?: 0
                     if (heightDiff > 200) { // You can adjust this threshold as needed
                         val layoutParams = bottomSheetFragment.view?.layoutParams
                         layoutParams?.height = rootView.height
                         bottomSheetFragment.view?.layoutParams = layoutParams
                     }
                 }
             }
         })*/
    }


    fun init() {

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

        binding.chipGroupTags.setOnCheckedStateChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(chipGroup.checkedChipId)?.text
//            Toast.makeText(chipGroup.context, titleOrNull ?: "No Choice", Toast.LENGTH_LONG).show()
            when (chipGroup.checkedChipId) {
                R.id.chip_looking_for_partner -> {
                    // TODO: Check if Location permission is granted
                    binding.list.list.visibility = View.GONE
                    binding.enableLocation.container.visibility = View.VISIBLE


                }

                R.id.chip_rented_rooms -> {
                    binding.list.list.visibility = View.VISIBLE
                    binding.enableLocation.container.visibility = View.GONE
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.delayOnLifecycle(1000) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        binding.enableLocation.btnAllow.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_home_to_lookingForPartnerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}