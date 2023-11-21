package com.rpn.totel.ui.fragment.extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rpn.totel.databinding.DialogRefundInitatedBinding

class RefundInitatedFragment : DialogFragment() {


    private var _binding: DialogRefundInitatedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(true) // Prevent closing on outside touch
        return dialog
    }
*/
//    override fun getTheme(): Int = R.style.DialogSheet


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogRefundInitatedBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun onClick() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    fun init() {
    }

}
