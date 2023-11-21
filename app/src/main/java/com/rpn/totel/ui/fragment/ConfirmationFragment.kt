package com.rpn.totel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.adapters.secondary.MessagesAdapter
import com.rpn.totel.databinding.FragmentHomeBinding
import com.rpn.totel.databinding.LayoutRecyclerviewBinding
import com.rpn.totel.databinding.LayoutRecyclerviewHomeBinding
import com.rpn.totel.model.Messages
import com.rpn.totel.ui.viewmodel.HomeViewModel

class ConfirmationFragment : Fragment() {

    private var messagesAdapter: MessagesAdapter? = MessagesAdapter()
    private var _binding: LayoutRecyclerviewHomeBinding? = null

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

        _binding = LayoutRecyclerviewHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {

        messagesAdapter?.updateDataSet(
            listOf(
                Messages("Author"),
                Messages("Author"),
                Messages("Author"),
                Messages("Author")
            )
        )
        binding.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = messagesAdapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}