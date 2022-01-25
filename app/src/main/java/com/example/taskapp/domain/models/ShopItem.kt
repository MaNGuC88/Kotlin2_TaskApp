package com.example.taskapp.domain.models

data class ShopItem(
    var name: String,
    var count: Int,
    var enabled: Boolean,
    var id: Int = 0
)