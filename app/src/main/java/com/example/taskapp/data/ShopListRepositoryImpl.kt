package com.example.taskapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.repository.ShopListRepository
import com.example.taskapp.presentation.App

object ShopListRepositoryImpl: ShopListRepository {

//    private val shopListLD = MutableLiveData<List<ShopItem>>()
//    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})

//    private var autoIncrementId = 0
    private val mapper = ShopListMapper()

//    init {
//        for (i in 0 until 100) {
//            val item = ShopItem("Name $i", i, true)
//            addShopItem(item)
//        }
//    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        App.appDataBase.shopListDao().insertShopItem(dbModel)
//        if (shopItem.id == ShopItem.UNDEFINED_ID) {
//            shopItem.id = autoIncrementId++
//        }
//        shopList.add(shopItem)
//        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        App.appDataBase.shopListDao().getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

//    private fun updateList() {
//        shopListLD.value = shopList.toList()
//    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        App.appDataBase.shopListDao().deleteShopItem(dbModel)
//        shopList.remove(shopItem)
//        updateList()
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        App.appDataBase.shopListDao().updateShopItem(dbModel)
//        val oldElement = getShopItem(shopItem.id)
//        shopList.remove(oldElement)
//        addShopItem(shopItem)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
//        return shopList.find { shopItem ->
//            shopItem.id == shopItemId
//        } ?: throw RuntimeException("Element with id $shopItemId not found")
        val dbModel = App.appDataBase.shopListDao().getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

}