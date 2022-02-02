package com.example.taskapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.domain.models.ShopItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopListAdapter @Inject constructor() :
    ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ShopItemCallback()) {

   var longClick: ((ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (!shopItem.enabled) {
            ENABLED
        } else {
            DISABLED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLED -> R.layout.item_enabled
            DISABLED -> R.layout.item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ShopItemViewHolder(LayoutInflater.from(parent.context).inflate
            (layout, parent, false))
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

        holder.itemView.setOnLongClickListener {
            longClick?.invoke(shopItem)
            return@setOnLongClickListener true
        }
    }

    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvCount: TextView = itemView.findViewById(R.id.tv_count)
    }

    companion object {
        private const val ENABLED = 0
        private const val DISABLED = 1
    }
}