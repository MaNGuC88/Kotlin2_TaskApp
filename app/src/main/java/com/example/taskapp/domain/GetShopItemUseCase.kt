package com.example.taskapp.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem() = shopListRepository.getShopItem()

}