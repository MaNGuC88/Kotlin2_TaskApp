package com.example.taskapp.presentation.main

import androidx.lifecycle.ViewModel
import com.example.taskapp.data.ShopListRepositoryImpl
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.use_cases.DeleteShopItemUseCase
import com.example.taskapp.domain.use_cases.EditShopItemUseCase
import com.example.taskapp.domain.use_cases.GetShopListUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }


//    fun getShopList() {
//        val list = getShopListUseCase.getShopList()
//        shopList.value = list
//    }

}

