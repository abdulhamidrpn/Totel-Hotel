package com.rpn.totel.ui.fragment.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.HomeAdapter
import com.rpn.totel.adapters.secondary.WISHLIST_TYPE_NORMAL
import com.rpn.totel.adapters.secondary.WishlistAdapter
import com.rpn.totel.databinding.FragmentProfileWishlistBinding
import com.rpn.totel.databinding.FragmentWishlistBinding
import com.rpn.totel.extensions.delayOnLifecycle
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Claims
import com.rpn.totel.ui.fragment.extra.AddToWishlistFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel

class WishlistFragment : Fragment() {

    private var rvAdapter: WishlistAdapter? = WishlistAdapter(WISHLIST_TYPE_NORMAL)

    private var _binding: FragmentWishlistBinding? = null

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

        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.delayOnLifecycle(1000) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}