package com.rpn.totel.ui.fragment.extra

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.WISHLIST_TYPE_SMALL
import com.rpn.totel.adapters.secondary.WishlistAdapter
import com.rpn.totel.databinding.DialogCalendarBinding
import com.rpn.totel.databinding.Example4CalendarDayBinding
import com.rpn.totel.databinding.Example4CalendarHeaderBinding
import com.rpn.totel.extensions.addStatusBarColorUpdate
import com.rpn.totel.extensions.getDrawableCompat
import com.rpn.totel.extensions.makeInVisible
import com.rpn.totel.extensions.makeVisible
import com.rpn.totel.extensions.screenHeight
import com.rpn.totel.extensions.setTextColorRes
import com.rpn.totel.utils.ContinuousSelectionHelper.getSelection
import com.rpn.totel.utils.ContinuousSelectionHelper.isInDateBetweenSelection
import com.rpn.totel.utils.ContinuousSelectionHelper.isOutDateBetweenSelection
import com.rpn.totel.utils.DateSelection
import com.rpn.totel.utils.displayText
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : BottomSheetDialogFragment() {


    private val behavior: BottomSheetBehavior<View> by lazy { BottomSheetBehavior() }

    private var _binding: DialogCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val today = LocalDate.now()

    private var selection = DateSelection()

    private val headerDateFormatter = DateTimeFormatter.ofPattern("EEE'\n'd MMM")

    /* override fun getTheme(): Int {
         return R.style.CustomBottomSheetDialogTheme
     }*/
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false) // Prevent closing on outside touch
        dialog.setOnShowListener {
            behavior.isFitToContents = true
            behavior.setPeekHeight(screenHeight,true)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addStatusBarColorUpdate(R.color.colorPrimary)
        init()
    }
    fun init() {
        /*val bottomSheetBehavior = BottomSheetBehavior.from(binding.root)
        // Set the peek height to MATCH_PARENT
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        // Optional: Set state and callback
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED*/



        binding.btnClose.setOnClickListener {
            // Close the bottom sheet when the close button is clicked
            dismiss()
        }
        setUpCalender()
    }


    fun setUpCalender() {

        // Set the First day of week depending on Locale
        val daysOfWeek = daysOfWeek()
        binding.legendLayout.root.children.forEachIndexed { index, child ->
            (child as TextView).apply {
                text = daysOfWeek[index].displayText()
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                setTextColorRes(R.color.example_4_grey)
            }
        }

        configureBinders()
        val currentMonth = YearMonth.now()
        binding.exFourCalendar.setup(
            currentMonth,
            currentMonth.plusMonths(12),
            daysOfWeek.first(),
        )
    }


    private fun bindSummaryViews() {
        binding.exFourStartDateText.apply {
            if (selection.startDate != null) {
                text = headerDateFormatter.format(selection.startDate)
                setTextColorRes(R.color.colorPrimary)
            } else {
                text = getString(R.string.start_date)
                setTextColor(Color.GRAY)
            }
        }

        binding.exFourEndDateText.apply {
            if (selection.endDate != null) {
                text = headerDateFormatter.format(selection.endDate)
                setTextColorRes(R.color.colorPrimary)
            } else {
                text = getString(R.string.end_date)
                setTextColor(Color.GRAY)
            }
        }

        binding.exFourSaveButton.isEnabled = selection.daysBetween != null
    }

    private fun configureBinders() {
        val clipLevelHalf = 5000
        val ctx = requireContext()
        val rangeStartBackground =
            ctx.getDrawableCompat(R.drawable.example_4_continuous_selected_bg_start).also {
                it.level = clipLevelHalf // Used by ClipDrawable
            }
        val rangeEndBackground =
            ctx.getDrawableCompat(R.drawable.example_4_continuous_selected_bg_end).also {
                it.level = clipLevelHalf // Used by ClipDrawable
            }
        val rangeMiddleBackground =
            ctx.getDrawableCompat(R.drawable.example_4_continuous_selected_bg_middle)
        val singleBackground = ctx.getDrawableCompat(R.drawable.example_4_single_selected_bg)
        val todayBackground = ctx.getDrawableCompat(R.drawable.example_4_today_bg)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = Example4CalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate &&
                        (day.date == today || day.date.isAfter(today))
                    ) {
                        selection = getSelection(
                            clickedDate = day.date,
                            dateSelection = selection,
                        )
                        this@CalendarFragment.binding.exFourCalendar.notifyCalendarChanged()
                        bindSummaryViews()
                    }
                }
            }
        }

        binding.exFourCalendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val textView = container.binding.exFourDayText
                val roundBgView = container.binding.exFourRoundBackgroundView
                val continuousBgView = container.binding.exFourContinuousBackgroundView

                textView.text = null
                roundBgView.makeInVisible()
                continuousBgView.makeInVisible()

                val (startDate, endDate) = selection

                when (data.position) {
                    DayPosition.MonthDate -> {
                        textView.text = data.date.dayOfMonth.toString()
                        if (data.date.isBefore(today)) {
                            textView.setTextColorRes(R.color.example_4_grey_past)
                        } else {
                            when {
                                startDate == data.date && endDate == null -> {
                                    textView.setTextColorRes(R.color.white)
                                    roundBgView.applyBackground(singleBackground)
                                }

                                data.date == startDate -> {
                                    textView.setTextColorRes(R.color.white)
                                    continuousBgView.applyBackground(rangeStartBackground)
                                    roundBgView.applyBackground(singleBackground)
                                }

                                startDate != null && endDate != null && (data.date > startDate && data.date < endDate) -> {
                                    textView.setTextColorRes(R.color.example_4_grey)
                                    continuousBgView.applyBackground(rangeMiddleBackground)
                                }

                                data.date == endDate -> {
                                    textView.setTextColorRes(R.color.white)
                                    continuousBgView.applyBackground(rangeEndBackground)
                                    roundBgView.applyBackground(singleBackground)
                                }

                                data.date == today -> {
                                    textView.setTextColorRes(R.color.example_4_grey)
                                    roundBgView.applyBackground(todayBackground)
                                }

                                else -> textView.setTextColorRes(R.color.example_4_grey)
                            }
                        }
                    }
                    // Make the coloured selection background continuous on the
                    // invisible in and out dates across various months.
                    DayPosition.InDate ->
                        if (startDate != null && endDate != null &&
                            isInDateBetweenSelection(data.date, startDate, endDate)
                        ) {
                            continuousBgView.applyBackground(rangeMiddleBackground)
                        }

                    DayPosition.OutDate ->
                        if (startDate != null && endDate != null &&
                            isOutDateBetweenSelection(data.date, startDate, endDate)
                        ) {
                            continuousBgView.applyBackground(rangeMiddleBackground)
                        }
                }
            }

            private fun View.applyBackground(drawable: Drawable) {
                makeVisible()
                background = drawable
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val textView = Example4CalendarHeaderBinding.bind(view).exFourHeaderText
        }
        binding.exFourCalendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    container.textView.text = data.yearMonth.displayText()
                }
            }
    }
}
