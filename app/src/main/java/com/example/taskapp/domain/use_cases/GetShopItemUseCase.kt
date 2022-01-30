package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int) = shopListRepository.getShopItem(shopItemId)

}