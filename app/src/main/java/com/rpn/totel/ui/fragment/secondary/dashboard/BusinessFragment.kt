package com.rpn.totel.ui.fragment.secondary.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.rpn.totel.adapters.view_pager.ViewPagerAdapter
import com.rpn.totel.databinding.FragmentBusinessBinding
import com.rpn.totel.ui.fragment.secondary.dashboard.business.EarningsFragment
import com.rpn.totel.ui.fragment.secondary.dashboard.business.ReviewsFragment
import com.rpn.totel.ui.viewmodel.BookingViewModel

class BusinessFragment : Fragment() {

    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: BookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(BookingViewModel::class.java)

        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
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


    private fun init() {

        initViewpager(binding.pagerSortMode)
        binding.tabsForYou.setupWithViewPager(binding.pagerSortMode)
        binding.pagerSortMode.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                viewModel.pagePosition.postValue(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
//                enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE)
            }

        })

    }
    private fun initViewpager(viewPager: ViewPager) {
        val listSortModeAdapter =
            ViewPagerAdapter(childFragmentManager).apply {
                addFragment(EarningsFragment(), "Earnings")
                addFragment(ReviewsFragment(), "Reviews")
            }


        viewPager.apply {
            adapter = listSortModeAdapter
            offscreenPageLimit = 2

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageSelected(position: Int) = Unit

            })
            setCurrentItem(0, true)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}