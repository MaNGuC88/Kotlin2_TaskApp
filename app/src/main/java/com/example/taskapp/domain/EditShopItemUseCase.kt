package com.example.taskapp.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem() = shopListRepository.editShopItem()

}