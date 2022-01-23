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

    private var newList = mutableListOf<ShopItem>()

    init {
        newList.addAll(list)
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
        val shopItem = list[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    fun swap(listData: LiveData<List<ShopItem>>) {
        val newList = mutableListOf<ShopItem>()
        listData.value?.forEach {
            newList.add(it.copy())
        }
        val diffCallback = DiffCallback(this.list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvCount: TextView = itemView.findViewById(R.id.tv_count)

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