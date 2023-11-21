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
import com.rpn.totel.adapters.secondary.CardAddAdapter
import com.rpn.totel.databinding.FragmentPaymentBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Item
import com.rpn.totel.ui.fragment.extra.AddNewCardFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel
import com.rpn.totel.utils.Constants

class PaymentFragment : Fragment() {

    private var rvAdapterCard: CardAddAdapter? = CardAddAdapter()
    private var rvAdapterPaymentOption: CardAddAdapter? = CardAddAdapter()

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
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
            btnPayment.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_paymentStatusFragment)
            }
            tvAddNewCard.setOnClickListener {
                val bottomSheetFragment = AddNewCardFragment()
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            }
        }
    }


    fun init() {

        rvAdapterCard?.itemClickListener = object : ItemClickListener<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {

            }
        }
        rvAdapterCard?.updateDataSet(Constants.demoCardList)
        binding.listCard.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterCard
        }




        rvAdapterPaymentOption?.itemClickListener = object : ItemClickListener<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {

            }
        }
        rvAdapterPaymentOption?.updateDataSet(Constants.demoPaymentMethodList)
        binding.listPaymentMethod.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterPaymentOption
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}