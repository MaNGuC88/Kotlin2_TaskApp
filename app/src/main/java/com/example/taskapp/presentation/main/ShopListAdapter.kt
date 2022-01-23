package com.example.taskapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.domain.ShopItem

class ShopListAdapter(var longClick: (ShopItem) -> Unit) :
    RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var list = mutableListOf<ShopItem>()
        set(value) {
            field = value
        }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].enabled) {
            ENABLED
        } else {
            DISABLED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return when (viewType) {
            ENABLED -> {
                ShopItemViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_enabled, parent, false))
            }
            DISABLED -> {
                ShopItemViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_disabled, parent, false))
            }
            else -> {
                ShopItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_enabled, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bind(shopItem = list[position])
    }

    fun swap(listData: LiveData<List<ShopItem>>) {
        val changedList = mutableListOf<ShopItem>()
        listData.value?.forEach {
            changedList.add(it.copy())
        }
        list.clear()
        list.addAll(changedList)
        val diffCallback = DiffCallback(list, changedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvCount: TextView = itemView.findViewById(R.id.tv_count)

        fun bind(shopItem: ShopItem) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }

        init {
            itemView.setOnLongClickListener {
                longClick.invoke(list[absoluteAdapterPosition])
                return@setOnLongClickListener true
            }
        }
    }

    companion object {
        private const val ENABLED = 0
        private const val DISABLED = 1
    }
}