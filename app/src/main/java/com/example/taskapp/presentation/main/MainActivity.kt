package com.example.taskapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.domain.models.ShopItem
import com.example.taskapp.presentation.insert.InsertActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var adapterShop: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObservers()
        initListeners()
        setUpRecycler()
    }

    private fun initObservers() {
        viewModel.shopList.observe(this, { listData ->
            adapterShop.submitList(listData)
        })
    }

    private fun setUpRecycler() {
        with(binding.mainRv) {
            adapterShop.longClick = {
                viewModel.changeEnableState(it)
            }
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
                val item = adapterShop.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun initListeners() {
        binding.fab.setOnClickListener {
            Intent(this, InsertActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.getShopItem(query.toInt())
                    viewModel.shopItem.observe(this@MainActivity) {
                        Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

}