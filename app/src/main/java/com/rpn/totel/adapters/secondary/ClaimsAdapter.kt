package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemClaimsBinding
import com.rpn.totel.model.Claims


class ClaimsAdapter : RecyclerView.Adapter<ClaimsAdapter.ViewHolder>() {

    var list: MutableList<Claims> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_claims,
            parent,
            false
        ) as ItemClaimsBinding

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Claims>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(claims: Claims) {

        }

        override fun onClick(view: View?) {

        }

    }
}