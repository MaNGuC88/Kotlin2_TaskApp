package com.example.taskapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDBModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val count: Int,
    var enabled: Boolean,
)