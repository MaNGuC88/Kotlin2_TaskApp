package com.example.taskapp.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.example.taskapp.domain.ShopItem

class DiffCallback(
    private val oldList: MutableList<ShopItem>,
    private val newList: MutableList<ShopItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}