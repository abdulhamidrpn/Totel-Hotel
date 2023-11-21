package com.rpn.totel.adapters.secondary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rpn.totel.R
import com.rpn.totel.databinding.ItemNotificationBinding
import com.rpn.totel.interfaces.ItemClickListener
import com.rpn.totel.interfaces.ItemNotificationClickListener
import com.rpn.totel.model.Claims
import com.rpn.totel.model.Notifiations


class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    var list: MutableList<Notifiations> = mutableListOf()


    var itemClickListener: ItemNotificationClickListener<Notifiations>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_notification,
            parent,
            false
        ) as ItemNotificationBinding

        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newList: List<Notifiations>) {
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(notifiations: Notifiations) {
            binding as ItemNotificationBinding
            binding.apply {
                if (notifiations.delayTime != null) {
                    containerTimer.visibility = View.VISIBLE
                    ivNotification.visibility = View.GONE
                    tvNotificationTime.visibility = View.GONE
                }else {
                    containerTimer.visibility = View.GONE
                    ivNotification.visibility = View.VISIBLE
                    tvNotificationTime.visibility = View.VISIBLE

                }

                if (notifiations.time != null) {
                    tvTimeline.visibility = View.VISIBLE
                    tvTimeline.text = notifiations.time
                }else{
                    tvTimeline.visibility = View.GONE
                }


                container.setOnClickListener{
                    if (notifiations.delayTime != null) {
                        itemClickListener?.onItemDurationAlertClick(it,adapterPosition,notifiations)
                    }else {
                        itemClickListener?.onItemClick(it,adapterPosition,notifiations)
                    }
                }
            }
        }

        override fun onClick(view: View?) {

        }

    }
}