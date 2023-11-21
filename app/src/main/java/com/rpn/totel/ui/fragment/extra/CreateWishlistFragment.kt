package com.rpn.totel.ui.fragment.extra

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rpn.totel.databinding.DialogCreateWishlistBinding

class CreateWishlistFragment : BottomSheetDialogFragment() {


    private var _binding: DialogCreateWishlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    /* override fun getTheme(): Int {
         return R.style.CustomBottomSheetDialogTheme
     }*/
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false) // Prevent closing on outside touch
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogCreateWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        binding.btnClose.setOnClickListener {
            // Close the bottom sheet when the close button is clicked
            dismiss()
        }
        binding.btnCreate.setOnClickListener {
            // Close the bottom sheet when the close button is clicked
            dismiss()
        }
    }

}
