package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpn.totel.R
import com.rpn.totel.adapters.ItemAdapter
import com.rpn.totel.adapters.secondary.THUMBNAIL_TYPE_LARGE
import com.rpn.totel.adapters.secondary.ThumbnailAdapter
import com.rpn.totel.databinding.FragmentHomeRoomBookingBinding
import com.rpn.totel.databinding.FragmentHomeRoomDetailsBinding
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Item
import com.rpn.totel.ui.viewmodel.HomeViewModel

class RoomBookingFragment : Fragment() {

    private var _binding: FragmentHomeRoomBookingBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeRoomBookingBinding.inflate(inflater, container, false)
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
        spinnerSet()
        spinnerSetGuests()

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.globalActionToRoomLocationFragment)
        }

    }

    fun spinnerSet() {

        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.item_text,
            getResources().getStringArray(R.array.duration)
        )
        adapter.setDropDownViewResource(R.layout.item_text)

        binding.apply {
            spinner.adapter = adapter
            spinner.setSelection(0)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }
            }
        }

    }
    fun spinnerSetGuests() {

        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.item_text,
            getResources().getStringArray(R.array.guests)
        )
        adapter.setDropDownViewResource(R.layout.item_text)

        binding.apply {
            spinnerGuests.adapter = adapter
            spinnerGuests.setSelection(0)
            spinnerGuests.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}