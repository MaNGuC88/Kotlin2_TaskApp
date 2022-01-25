package com.example.taskapp.domain.use_cases

import com.example.taskapp.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList() = shopListRepository.getShopList()

}