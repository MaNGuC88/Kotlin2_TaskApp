package com.example.taskapp.presentation.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.presentation.insert.InsertActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterShop: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()
        initListeners()
        setUpRecycler()

        goToInsertActivity()

        handleSearch(intent)
    }

    private fun goToInsertActivity() {
        binding.fab.setOnClickListener {
            Intent(this, InsertActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleSearch(intent)
    }

    private fun handleSearch(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (query != null) {
                viewModel.getShopItem(query.toInt())
                viewModel.shopItem.observe(this, {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    private fun initObservers() {
        viewModel.shopList.observe(this, { listData ->
//            adapterShop.list = listData as MutableList<ShopItem>
            adapterShop.submitList(listData)
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
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapterShop.list[viewHolder.absoluteAdapterPosition]
                val item = adapterShop.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun longClickListener(shopItem: ShopItem) {
        viewModel.changeEnableState(shopItem)
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