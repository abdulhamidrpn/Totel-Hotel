package com.rpn.totel.ui.fragment.extra

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.CardAddAdapter
import com.rpn.totel.databinding.DialogAddNewCardBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Item

class AddNewCardFragment : BottomSheetDialogFragment() {

    private var rvAdapter: CardAddAdapter? = CardAddAdapter()

    private var _binding: DialogAddNewCardBinding? = null

    var cardItemClickListener: ItemClickListener<Item>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(true) // Prevent closing on outside touch
        return dialog
    }
*/
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogAddNewCardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }

    fun onClick() {

    }

    fun init() {

        binding.btnAddNewCard.setOnClickListener {

            cardItemClickListener?.onItemClick(it, 0, Item(0,"Add New Card"))
        }
    }

}
