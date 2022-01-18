package com.example.taskapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.domain.ShopItem

class MainActivity : AppCompatActivity((R.layout.activity_main)) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.shopList.observe(this, { list ->
            if (list.isEmpty()) {
                Toast.makeText(applicationContext, "List is empty", Toast.LENGTH_SHORT).show()
            } else {
                list.forEach { shopItem ->
                    Log.e("Naima", "list: $shopItem")
                    Toast.makeText(applicationContext, shopItem.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        initListeners()

        viewModel.shopItem.observe(this, {
            Log.e("Naima2", it.toString())
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListeners() {
        binding.addBtn.setOnClickListener {
            addShopItemFun()
        }
        binding.deleteBtn.setOnClickListener {
            deleteShopItemFun()
        }
        binding.editBtn.setOnClickListener {
            editShopItemFun()
        }
        binding.getBtn.setOnClickListener {
            getShopItemFun()
        }
    }

    private fun addShopItemFun() {
        viewModel.addShopItem(ShopItem("Potato", 2, false, 1))
        viewModel.getShopList()
    }

    private fun deleteShopItemFun() {
        viewModel.deleteShopItem()
        viewModel.getShopList()
    }

    private fun editShopItemFun() {
        viewModel.editShopItem()
        viewModel.getShopList()
    }

    private fun getShopItemFun() {
        viewModel.getShopItem()
    }

}