package com.example.taskapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = UNDEFINED,
    var name: String,
    var count: Int,
    var enabled: Boolean,
) {
    companion object {
        const val UNDEFINED = 0
    }
}