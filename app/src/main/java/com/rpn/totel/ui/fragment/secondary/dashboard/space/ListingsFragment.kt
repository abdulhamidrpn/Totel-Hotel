package com.rpn.totel.ui.fragment.secondary.dashboard.space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.adapters.secondary.ListingsAdapter
import com.rpn.totel.databinding.LayoutRecyclerviewBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.model.Listings

class ListingsFragment : Fragment() {

    private var rvAdapter: ListingsAdapter? = ListingsAdapter()
    private var _binding: LayoutRecyclerviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = LayoutRecyclerviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initmessages()
    }

    fun initmessages() {

        rvAdapter?.updateDataSet(
            listOf(
                Listings("Author"),
                Listings("Author"),
                Listings("Author"),
                Listings("Author")
            )
        )
        binding.list.apply {
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