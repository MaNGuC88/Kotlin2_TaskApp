package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository
import com.example.taskapp.domain.models.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }

}