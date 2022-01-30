package com.example.taskapp.presentation.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityInsertBinding
import com.example.taskapp.domain.models.ShopItem

class InsertActivity : AppCompatActivity(R.layout.activity_insert) {

    private val binding: ActivityInsertBinding by viewBinding()
    private val viewModel: InsertViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        initListeners()
    }

    private fun initListeners() {
        binding.btnInsert.setOnClickListener {
            insertShopItem()
            finish()
        }
    }

    private fun insertShopItem() {
        if (binding.tvName.text.isNotEmpty() && binding.tvCount.text.isNotEmpty()) {
            val name = binding.tvName.text.toString()
            val count = binding.tvCount.text.toString().toInt()
            val enabled = false
            viewModel.addShopItem(ShopItem(name, count, enabled))
        }
    }
}