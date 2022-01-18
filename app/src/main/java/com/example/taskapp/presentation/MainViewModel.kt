package com.example.taskapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskapp.data.ShopListRepositoryImpl
import com.example.taskapp.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()
    val shopItem = MutableLiveData<ShopItem>()

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem() {
        if (shopList.value!!.isNotEmpty()) {
            deleteShopItemUseCase.deleteShopItem(shopList.value!![0])
        }
    }

    fun editShopItem() {
        shopList.value!![0].enabled = editShopItemUseCase.editShopItem()
    }

    fun getShopItem() {
        shopItem.value = shopList.value!![getShopItemUseCase.getShopItem()]
    }

}

