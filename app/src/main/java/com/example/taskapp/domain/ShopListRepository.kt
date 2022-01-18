package com.example.taskapp.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun getShopList(): List<ShopItem>

    fun editShopItem(): Boolean

    fun getShopItem(): Int

}