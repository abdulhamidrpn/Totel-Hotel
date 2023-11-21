package com.rpn.totel.ui.fragment.secondary

import android.content.res.ColorStateList
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
import com.rpn.totel.databinding.FragmentDurationAlertBinding
import com.rpn.totel.databinding.FragmentExtendTimeBinding
import com.rpn.totel.databinding.FragmentProfileWishlistBinding
import com.rpn.totel.databinding.FragmentWishlistBinding
import com.rpn.totel.extensions.delayOnLifecycle
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Claims
import com.rpn.totel.ui.fragment.extra.AddToWishlistFragment
import com.rpn.totel.ui.viewmodel.HomeViewModel

class ExtendTimeFragment : Fragment() {

    private var _binding: FragmentExtendTimeBinding? = null

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

        _binding = FragmentExtendTimeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.delayOnLifecycle(1000) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
        binding.chip1.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                // Chip is selected.
                binding.chip1.chipBackgroundColor =ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            } else {
                // Chip is not selected.
                binding.chip1.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.background_button))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}