package com.example.taskapp.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.example.taskapp.domain.models.ShopItem

class ShopItemCallback: DiffUtil.ItemCallback<ShopItem> () {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}