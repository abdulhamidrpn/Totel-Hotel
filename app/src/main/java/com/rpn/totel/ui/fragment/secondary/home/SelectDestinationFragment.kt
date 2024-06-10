package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.BookedAdapter
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_LARGE
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_NORMAL
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_SMALL
import com.rpn.totel.adapters.secondary.ThumbnailAdapter
import com.rpn.totel.databinding.FragmentHomeSelectDestinationBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.rpn.totel.utils.Constants

class SelectDestinationFragment : Fragment() {

    private var rvAdapterPopularDestination: ThumbnailAdapter? = ThumbnailAdapter(THUMBNAIL_TYPE_SMALL)
    private var rvAdapterYouMayLike: ThumbnailAdapter? = ThumbnailAdapter(THUMBNAIL_TYPE_NORMAL)
    private var _binding: FragmentHomeSelectDestinationBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeSelectDestinationBinding.inflate(inflater, container, false)
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

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.globalActionToSelectPartnerFragment)
        }
    }

    fun init() {

        rvAdapterPopularDestination?.updateDataSet(Constants.demoItemList.shuffled())
        binding.listPopularDestinations.list.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = rvAdapterPopularDestination
        }

        rvAdapterYouMayLike?.updateDataSet(Constants.demoItemList.shuffled())
        binding.listYouMayLike.list.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterYouMayLike
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}