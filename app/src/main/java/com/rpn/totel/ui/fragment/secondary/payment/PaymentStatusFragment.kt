package com.rpn.totel.ui.fragment.secondary.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.CardAddAdapter
import com.rpn.totel.databinding.FragmentPaymentBinding
import com.rpn.totel.databinding.FragmentPaymentStatusBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Item
import com.rpn.totel.ui.fragment.extra.AddNewCardFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel
import com.rpn.totel.utils.Constants

class PaymentStatusFragment : Fragment() {

    private var _binding: FragmentPaymentStatusBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentPaymentStatusBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        binding.apply {
            btnSkip.setOnClickListener {
                findNavController().navigate(R.id.action_paymentStatusFragment_to_bookingStatusFragment)
            }
        }
    }


    fun init() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}