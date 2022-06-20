package br.com.cwi.nespresso_app.presentation.products.accessory

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.entity.ProductType
import br.com.cwi.nespresso_app.data.repository.CoffeeRepository
import br.com.cwi.nespresso_app.databinding.ActivityAccessoryBinding
import br.com.cwi.nespresso_app.presentation.base.BaseBottomNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccessoryActivity : BaseBottomNavigation() {
    private lateinit var binding: ActivityAccessoryBinding

    private val repository = CoffeeRepository()

    override val currentTab: Int = R.id.products_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpAccessoriesList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getBottomNavigation() = binding.bottomNavigation

    private fun setUpAccessoriesList() {

        CoroutineScope(Dispatchers.Main).launch {
            repository.getAccessories().let { categoryList ->
                val recyclerView = binding.rvAccessories

                recyclerView.addItemDecoration(
                    DividerItemDecoration(this@AccessoryActivity, DividerItemDecoration.VERTICAL)
                )

                val accessoryList = mutableListOf<ProductType>()

                categoryList.forEach {
                    accessoryList.add(it)
                    accessoryList.addAll(it.accessories)
                }

                recyclerView.adapter = AccessoryAdapter(this@AccessoryActivity, accessoryList)
            }
        }
    }
}