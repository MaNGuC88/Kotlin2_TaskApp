package com.example.taskapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.taskapp.domain.models.ShopItem
import dagger.Binds
import javax.inject.Inject

interface ShopListRepository{

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

}