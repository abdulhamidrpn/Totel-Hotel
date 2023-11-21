package com.rpn.totel.ui.fragment.extra

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rpn.totel.R
import com.rpn.totel.adapters.secondary.WishlistAdapter
import com.rpn.totel.adapters.secondary.WISHLIST_TYPE_SMALL
import com.rpn.totel.databinding.DialogAddToWishlistBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.model.Claims

class AddToWishlistFragment : BottomSheetDialogFragment() {


    private var rvAdapter: WishlistAdapter? = WishlistAdapter(WISHLIST_TYPE_SMALL)

    private var _binding: DialogAddToWishlistBinding? = null

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

        _binding = DialogAddToWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {

        rvAdapter?.itemClickListener = object : ItemClickListener<Claims> {
            override fun onItemClick(view: View, position: Int, item: Claims) {
                findNavController().navigate(R.id.globalActionToWishlistFragment)
            }

            override fun onItemAddToWishlistClick(view: View, position: Int, item: Claims) {

            }
        }
        rvAdapter?.updateDataSet(
            listOf(
                Claims("Author"),
                Claims("Author"),
                Claims("Author"),
                Claims("Author"),
                Claims("Author"),
                Claims("Author"),
                Claims("Author")
            )
        )
        binding.list.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }

        binding.btnClose.setOnClickListener {
            // Close the bottom sheet when the close button is clicked
            dismiss()
        }
        binding.btnCreate.setOnClickListener {
            // Close the bottom sheet when the close button is clicked
            dismiss()
        }
        binding.containerCreateNewList.container.setOnClickListener {
            val bottomSheetFragment = CreateWishlistFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

}
