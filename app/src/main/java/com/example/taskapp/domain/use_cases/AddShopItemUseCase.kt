package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository
import com.example.taskapp.domain.models.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }

}