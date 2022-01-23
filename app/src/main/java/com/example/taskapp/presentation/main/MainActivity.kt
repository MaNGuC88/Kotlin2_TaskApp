package com.example.taskapp.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.domain.ShopItem

class MainActivity : AppCompatActivity((R.layout.activity_main)) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterShop: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()
        initListeners()
        setUpRecycler()
    }

    private fun initObservers() {
        viewModel.shopList.observe(this, { listData ->
            adapterShop.list = listData as MutableList<ShopItem>
        })
    }

    private fun setUpRecycler() {
        with(binding.mainRv) {
            adapterShop = ShopListAdapter(this@MainActivity::longClickListener)
            adapter = adapterShop
        }
        setupSwipeListener(binding.mainRv)
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    or ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapterShop.list[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
                adapterShop.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
                adapterShop.swap(viewModel.shopList)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun longClickListener(shopItem: ShopItem) {
        viewModel.changeEnableState(shopItem)
        adapterShop.notifyDataSetChanged()
        adapterShop.swap(viewModel.shopList)
    }

    private fun initListeners() {
//        binding.addBtn.setOnClickListener {
//            addShopItemFun()
//        }
//        binding.deleteBtn.setOnClickListener {
//            deleteShopItemFun()
//        }
//        binding.editBtn.setOnClickListener {
//            editShopItemFun()
//        }
//        binding.getBtn.setOnClickListener {
//            getShopItemFun()
//        }
    }

//    private fun addShopItemFun() {
//        viewModel.addShopItem(ShopItem("Potato", 2, false, 1))
//    }

}