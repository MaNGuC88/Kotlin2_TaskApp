package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository
import com.example.taskapp.domain.models.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) = shopListRepository.editShopItem(shopItem)

}