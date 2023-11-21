package com.rpn.totel.ui.fragment.secondary.dashboard.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.adapters.secondary.BookedAdapter
import com.rpn.totel.databinding.LayoutRecyclerviewBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.ui.viewmodel.HomeViewModel

class HistoryFragment : Fragment() {

    private var bookedAdapter: BookedAdapter? = BookedAdapter()
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

        initBooked()
    }

    fun initBooked() {

        bookedAdapter?.updateDataSet(
            listOf(
                Booked("Author"),
                Booked("Author"),
                Booked("Author"),
                Booked("Author")
            )
        )
        binding.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = bookedAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}