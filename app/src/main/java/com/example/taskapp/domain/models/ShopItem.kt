package com.example.taskapp.domain.models

import javax.inject.Inject

data class ShopItem @Inject constructor(
    var name: String,
    var count: Int,
    var enabled: Boolean,
    var id: Int = UNDEFINED
){
    companion object {
        const val UNDEFINED = 0
    }
}