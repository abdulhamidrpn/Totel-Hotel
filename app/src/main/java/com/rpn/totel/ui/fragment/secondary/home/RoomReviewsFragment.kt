package com.rpn.totel.ui.fragment.secondary.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.NearbyHotelAdapter
import com.rpn.totel.adapters.secondary.REVIEWS_TYPE_LARGE
import com.rpn.totel.adapters.secondary.ReviewsAdapter
import com.rpn.totel.customui.createDotIndicator
import com.rpn.totel.customui.updateDotIndicator
import com.rpn.totel.databinding.FragmentHomeRoomReviewsBinding
import com.rpn.totel.interfaces.SwipeHelperCallback
import com.rpn.totel.model.Booked
import com.rpn.totel.model.Reviews
import com.rpn.totel.ui.viewmodel.HomeViewModel

class RoomReviewsFragment : Fragment() {

    private var rvAdapterNearbyHotel: NearbyHotelAdapter? = NearbyHotelAdapter()
    private var rvAdapter: ReviewsAdapter? = ReviewsAdapter(REVIEWS_TYPE_LARGE)
    private var _binding: FragmentHomeRoomReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeRoomReviewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initNearbyHotel()
    }

    fun init() {


        val itemTouchHelper = ItemTouchHelper(SwipeHelperCallback(binding.list.list))
        itemTouchHelper.attachToRecyclerView(binding.list.list)

        rvAdapter?.updateDataSet(
            listOf(
                Reviews("Author"),
                Reviews("Author"),
                Reviews("Author"),
                Reviews("Author")
            )
        )
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = rvAdapter
        }


        // Inside your activity or fragment
        binding.list.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()

                // Update the dot indicator based on the current visible item
                requireContext().updateDotIndicator(firstVisibleItemPosition ?: 0,binding.dotIndicator)
            }
        })
        requireContext().createDotIndicator(4,binding.dotIndicator)
    }
    fun initNearbyHotel() {

        rvAdapterNearbyHotel?.updateDataSet(
            listOf(
                Booked("Author"),
                Booked("Author"),
                Booked("Author"),
                Booked("Author")
            )
        )
        binding.listNearbyHotels.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = rvAdapterNearbyHotel
        }

        // Inside your activity or fragment
        binding.listNearbyHotels.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()

                // Update the dot indicator based on the current visible item
                requireContext().updateDotIndicator(firstVisibleItemPosition ?: 0,binding.dotIndicatorNearbyHotels)
            }
        })
        requireContext().createDotIndicator(4,binding.dotIndicatorNearbyHotels)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}