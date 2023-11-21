package com.rpn.totel.ui.fragment.secondary.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.CardAddAdapter
import com.rpn.totel.databinding.FragmentBookingStatusBinding
import com.rpn.totel.databinding.FragmentPaymentBinding
import com.rpn.totel.databinding.FragmentPaymentStatusBinding
import com.rpn.totel.extensions.addStatusBarColorUpdate
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Item
import com.rpn.totel.ui.fragment.extra.AddNewCardFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel
import com.rpn.totel.utils.Constants

class BookingStatusFragment : Fragment() {

    private var _binding: FragmentBookingStatusBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentBookingStatusBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addStatusBarColorUpdate(R.color.colorPrimary)
        binding.let {
            it.viewModel = viewModel
            it.executePendingBindings()
            it.lifecycleOwner = this
        }
        onClick()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("TAG", "onDetach: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAG", "onStop: ")
    }
    fun onClick() {

        binding.apply {
            btnSkip.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSeeDetails.setOnClickListener {
                findNavController().navigate(R.id.action_bookingStatusFragment_to_bookingPaidDetailsFragment)
            }
        }
    }


    fun init() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TAG", "onDestroyView: ")
        _binding = null
    }
}