package com.rpn.totel.ui.fragment.secondary.dashboard.business

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentEarningsBinding
import com.rpn.totel.ui.viewmodel.HomeViewModel
import com.straiberry.android.charts.extenstions.Value
import com.straiberry.android.charts.extenstions.convertToChartScore
import com.straiberry.android.charts.extenstions.convertToLinearChartData
import com.straiberry.android.charts.extenstions.getMonthAndDay
import com.straiberry.android.charts.extenstions.toOralHygieneChart
import com.straiberry.android.charts.tooltip.SliderTooltip
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class EarningsFragment : Fragment() {

    private var _binding: FragmentEarningsBinding? = null
    private val binding get() = _binding!!

    private lateinit var sliderTooltip: SliderTooltip
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentEarningsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderTooltip = SliderTooltip()
        setupLinearChart()

    }

    private fun setupLinearChart() {
        // Chart data
        val currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        //Create a fake data for one month
        val remoteData = HashMap<String, List<String?>>()
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("11")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("10", "11")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("8", "9")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("9")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("7", "8")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("6")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("5", "4")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("4", "4")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("5", "5")
        calendar.add(Calendar.DATE, -1)
        remoteData[calendar.time.convertCurrentDateToChartDate(LINEAR_CHART_DATE_FORMAT)] =
            listOf("6", "7")

        // Convert data to chart input
        val lineChartData = remoteData.toOralHygieneChart()
        val convertedData = lineChartData.convertToLinearChartData()
        // Setup tooltip and chart color
        // In the triple data:
        // first = Drawable
        // second = date
        // third = score
        var isDataFake = false
        sliderTooltip.apply {
            // Get the first date that has a value
            val isDataSizeIsOne = convertedData.filter { it.third != 0.0f }.size == 1
            val firstData = convertedData.reversed().firstOrNull { it.third != 0.0f }
            if (isDataSizeIsOne && convertedData.reversed().first().third == 0.0f) {
                isDataFake = true
                convertedData[convertedData.size - 1] = Triple(
                    ColorDrawable(Color.TRANSPARENT),
                    firstData!!.second,
                    firstData.third
                )
            }
            var firstChartData: Value? = null
            var scoreAverage = ""
            var date = ""
            if (firstData != null) {
                // Get first score average
                scoreAverage = firstData.third.toInt().convertToChartScore()
                // Get first date
                date = firstData.second
                // Get all score if score are more then one
                firstChartData = lineChartData.first().value.reversed().firstOrNull {
                    it.score.size > 1 && it.date.toLong().getMonthAndDay() == firstData.second
                }
            }
            // Set the first two score to show on screen
            var scoreOne = ""
            var scoreTwo = ""
            if (firstChartData != null) {
                scoreOne = firstChartData.score[0].toString()
                scoreTwo = firstChartData.score[1].toString()
            }
            // Set tooltip data
            this.scoreAverage = scoreAverage
            this.date = date
            if (scoreOne != "")
                this.scoreOne = scoreOne
            if (scoreTwo != "")
                this.scoreTwo = scoreTwo
        }

        binding.linearChartViewOralHygiene.apply {
            isTooltipDraw = false
            tooltip = sliderTooltip
            isFake = isDataFake
            // Change colors of chart
            gradientFillColors = intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.primaryLight200),
                ContextCompat.getColor(requireContext(), R.color.secondaryLight)
            )
            lineColor = ContextCompat.getColor(requireContext(), R.color.white)
            // Set animation duration to draw the chart
            animation.duration = CHART_ANIMATION_DURATION
        }

        // If you are using view pager you can disable input touch to avoid
        // getting conflict with chart touch
//        binding.linearChartViewOralHygiene.onDataPointUnTouchListener = { _, _, _ ->
//            FragmentHome.viewPager2.isUserInputEnabled = true
//        }
        binding.linearChartViewOralHygiene.onDataPointTouchListener = { index, _, _ ->
//            FragmentHome.viewPager2.isUserInputEnabled = false
            val isFake = convertedData[index].first != null
            val scoreAverage = convertedData[index].third.toInt().convertToChartScore()
            val date = convertedData[index].second
            var scoreOne = ""
            var scoreTwo = ""
            if (lineChartData.first().value[index].score.size > 1) {
                scoreOne = lineChartData.first().value[index].score[0].toString()
                scoreTwo = lineChartData.first().value[index].score[1].toString()
            }
            binding.linearChartViewOralHygiene.tooltip = sliderTooltip.apply {
                this.isFake = isFake
                this.scoreAverage = scoreAverage
                this.date = date
                if (scoreOne != "") {
                    this.scoreOne = scoreOne
                }

                this.scoreTwo = scoreTwo
            }
        }

        binding.linearChartViewOralHygiene.animate(convertedData)
    }

    private fun Date.convertCurrentDateToChartDate(dataFormat: String): String {
        var day: String
        Calendar.getInstance().apply {
            time = this@convertCurrentDateToChartDate
            day = SimpleDateFormat(dataFormat, Locale.ENGLISH).apply {
                timeZone = TimeZone.getDefault()
            }.format(time)
        }
        return day
    }

    companion object {
        private const val LINEAR_CHART_DATE_FORMAT = "yyyy/MM/dd"
        private const val LINE_CHART_DATE_FORMAT = "yyyy-MM-dd"

        private const val CHART_ANIMATION_DURATION = 500L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}