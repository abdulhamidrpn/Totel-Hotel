package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rpn.totel.R
import com.rpn.totel.adapters.view_pager.SelectPartnerAdapter
import com.rpn.totel.databinding.FragmentHomeSelectPartnerBinding
import com.rpn.totel.interfaces.ItemClickListenerSingle
import com.rpn.totel.model.Item
import com.rpn.totel.ui.viewmodel.HomeViewModel

class SelectPartnerFragment : Fragment() {
    private var _binding: FragmentHomeSelectPartnerBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeSelectPartnerBinding.inflate(inflater, container, false)
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

        val items = listOf(
            Item(R.drawable.thumbnail_partner, "Ferdousi"),
            Item(R.drawable.thumbnail_men, "Abdul Hamid"),
            Item(R.drawable.thumbnail_women, "Hesshi")
        )

        val adapter = SelectPartnerAdapter(items, requireContext())
        adapter.itemClickListener = object : ItemClickListenerSingle<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
                findNavController().navigate(R.id.globalActionToProfileWishlistFragment)
            }
        }
        binding.viewpager.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}