package com.rpn.totel.ui.fragment.extra

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.CardAddAdapter
import com.rpn.totel.databinding.DialogAddCardBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Item
import com.rpn.totel.utils.Constants

class AddCardFragment : BottomSheetDialogFragment() {

    private var rvAdapter: CardAddAdapter? = CardAddAdapter()

    private var _binding: DialogAddCardBinding? = null

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

        _binding = DialogAddCardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }
    fun onClick(){

    }

    fun init() {

        rvAdapter?.itemClickListener = object : ItemClickListener<Item> {
            override fun onItemClick(view: View, position: Int, item: Item) {
//                findNavController().navigate(R.id.globalActionToWishlistFragment)

                cardItemClickListener?.onItemClick(view, position, item)
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Item) {

            }
        }
        rvAdapter?.updateDataSet(Constants.demoItemList.shuffled())
        binding.list.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }

}
