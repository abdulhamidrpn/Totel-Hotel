package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemBookedBinding
import com.rpn.totel.model.Booked


class BookedAdapter : RecyclerView.Adapter<BookedAdapter.ViewHolder>() {

    var bookedList: MutableList<Booked> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_booked,
            parent,
            false
        ) as ItemBookedBinding

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return bookedList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Booked>) {
        bookedList = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = bookedList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(Booked: Booked) {

        }

        override fun onClick(view: View?) {

        }

    }
}