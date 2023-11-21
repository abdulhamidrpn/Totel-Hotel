package com.rpn.totel.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.NotificationsAdapter
import com.rpn.totel.databinding.FragmentNotificationsBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.interfaces.ItemNotificationClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Notifiations
import com.rpn.totel.ui.fragment.extra.AddToWishlistFragment
import com.rpn.totel.ui.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private var rvAdapter: NotificationsAdapter? = NotificationsAdapter()
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {

        rvAdapter?.itemClickListener = object : ItemNotificationClickListener<Notifiations> {
            override fun onItemClick(view: View, position: Int, item: Notifiations) {
                findNavController().navigate(R.id.globalActionToRoomDetailsFragment)
            }

            override fun onItemDurationAlertClick(view: View, position: Int, item: Notifiations) {
                findNavController().navigate(R.id.globalActionToDurationAlertFragment)
            }
        }
        rvAdapter?.updateDataSet(
            listOf(
                Notifiations("Author", "Today","11:22"),
                Notifiations("Author"),
                Notifiations("Author"),
                Notifiations("Author", "Yesterday"),
                Notifiations("Author", "This week"),
                Notifiations("Author")
            )
        )
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed(
                Runnable { binding.swipeRefreshLayout.isRefreshing = false },
                1000
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}