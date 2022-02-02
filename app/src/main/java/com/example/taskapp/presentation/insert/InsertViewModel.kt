package com.example.taskapp.presentation.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.ShopListRepositoryImpl
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.domain.use_cases.AddShopItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(
    repository: ShopListRepositoryImpl
) : ViewModel() {

    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun addShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

}