package com.rpn.totel.ui.fragment.secondary.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.rpn.totel.adapters.view_pager.ViewPagerAdapter
import com.rpn.totel.databinding.FragmentInboxBinding
import com.rpn.totel.ui.fragment.secondary.dashboard.inbox.MessagesFragment
import com.rpn.totel.ui.fragment.secondary.dashboard.inbox.NotificationsFragment
import com.rpn.totel.ui.viewmodel.BookingViewModel

class InboxFragment : Fragment() {

    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: BookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(BookingViewModel::class.java)

        _binding = FragmentInboxBinding.inflate(inflater, container, false)
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
        initBookingPage()
    }

    fun onClick() {

//        binding.btnBooking.setOnClickListener {
//
//        }
    }


    private fun initBookingPage() {

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
                addFragment(MessagesFragment(), "Messages")
                addFragment(NotificationsFragment(), "Notifications")
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