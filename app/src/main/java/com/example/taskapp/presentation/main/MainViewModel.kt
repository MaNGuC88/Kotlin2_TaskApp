package com.example.taskapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.ShopListRepositoryImpl
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.use_cases.DeleteShopItemUseCase
import com.example.taskapp.domain.use_cases.EditShopItemUseCase
import com.example.taskapp.domain.use_cases.GetShopItemUseCase
import com.example.taskapp.domain.use_cases.GetShopListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: ShopListRepositoryImpl
) : ViewModel() {

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()
    val shopItem = MutableLiveData<ShopItem>()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun getShopItem(id: Int) {
        viewModelScope.launch {
            shopItem.postValue(getShopItemUseCase.getShopItem(id))
        }
    }

}

