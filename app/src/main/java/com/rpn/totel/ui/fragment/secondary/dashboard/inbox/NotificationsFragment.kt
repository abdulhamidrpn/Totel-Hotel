package com.rpn.totel.ui.fragment.secondary.dashboard.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.adapters.secondary.NotificationsAdapter
import com.rpn.totel.databinding.LayoutRecyclerviewBinding
import com.rpn.totel.model.Notifiations
import com.rpn.totel.ui.viewmodel.HomeViewModel

class NotificationsFragment : Fragment() {

    private var rvAdapter: NotificationsAdapter? = NotificationsAdapter()
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

        init()
    }

    fun init() {

        rvAdapter?.updateDataSet(
            listOf(
                Notifiations("Author","Today"),
                Notifiations("Author"),
                Notifiations("Author"),
                Notifiations("Author","Yesterday")
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