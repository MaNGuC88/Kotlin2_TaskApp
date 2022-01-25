package com.example.taskapp.presentation.insert

import androidx.lifecycle.ViewModel
import com.example.taskapp.data.ShopListRepositoryImpl
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.use_cases.AddShopItemUseCase

class InsertViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

}