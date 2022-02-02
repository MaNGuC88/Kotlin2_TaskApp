package com.example.taskapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.repository.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val mapper: ShopListMapper,
    private val dataBase: AppDataBase
) : ShopListRepository {

    override suspend fun addShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        dataBase.shopListDao().insertShopItem(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        dataBase.shopListDao().getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        dataBase.shopListDao().deleteShopItem(dbModel)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val dbModel = mapper.mapEntityToDbModel(shopItem)
        dataBase.shopListDao().updateShopItem(dbModel)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = dataBase.shopListDao().getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

}