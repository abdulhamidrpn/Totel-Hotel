package com.rpn.totel.ui.fragment.secondary

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rpn.totel.R
import com.rpn.totel.databinding.FragmentRefundPaymentBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Item
import com.rpn.totel.ui.fragment.extra.AddCardFragment
import com.rpn.totel.ui.fragment.extra.RefundInitatedFragment
import com.rpn.totel.ui.viewmodel.DashboardViewModel
import com.rpn.totel.utils.toast

class RefundPaymentFragment : Fragment() {
    private var _binding: FragmentRefundPaymentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentRefundPaymentBinding.inflate(inflater, container, false)
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

        binding.btnApplyToRefund.setOnClickListener {


            val bottomSheetFragment = AddCardFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            bottomSheetFragment.cardItemClickListener = object : ItemClickListener<Item> {
                override fun onItemClick(view: View, position: Int, item: Item) {
                    requireContext().toast("item clicked $position")
                    val dialogFragment = RefundInitatedFragment()
                    dialogFragment.setStyle(
                        DialogFragment.STYLE_NO_TITLE,
                        R.style.RoundedDialogFragmentTheme
                    )
                    dialogFragment.show(childFragmentManager, dialogFragment.tag)
                }

                override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {
                }
            }


        }
    }

    fun init() {

        val htmlTextView = binding.tvCancellationRefundPolicy


        // HTML-formatted text
        val htmlText =
            "<p>Free cancel anon until 3:00 PM on Dec 26. After that, cancel before 3:00 PM on Dec Mend get a 50% refund, minus the first night and service fee." +
                    "<b>Make sure this host's cancellation policy works for you.</b> and " +
                    "<i>For reservations made after March14, COVID-19 will not qualify as an extenuating circumstance</i> text.</p>" +
                    "<p>Click <a href=\"http://www.example.com\">here</a> for a link.</p>" +
                    "<p>Click <a href=\"http://www.google.com\"><u><font color=\"#FF0000\">here</font></u></a> for a link.</p>"

        // Set the HTML-formatted text to the TextView
        htmlTextView.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)

        // Enable clickable links
        htmlTextView.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}