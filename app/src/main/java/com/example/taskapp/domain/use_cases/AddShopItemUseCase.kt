package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository
import com.example.taskapp.domain.models.ShopItem
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }

}