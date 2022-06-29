package br.com.cwi.nespresso_app.presentation.feature.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity
import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity
import br.com.cwi.nespresso_app.data.database.entity.EntityType
import br.com.cwi.nespresso_app.domain.repository.AccessoryLocalRepository
import br.com.cwi.nespresso_app.domain.repository.CoffeeLocalRepository
import br.com.cwi.nespresso_app.presentation.base.BaseViewModel

class FavoritesViewModel (
    private val coffeeLocalRepository: CoffeeLocalRepository,
    private val accessoryLocalRepository: AccessoryLocalRepository
) : BaseViewModel() {

    private val _favorites = MutableLiveData<List<EntityType>>()
    val favorites: LiveData<List<EntityType>> = _favorites

    fun fetchFavorites() {
        launch {
            val coffeeList = coffeeLocalRepository.getAll()
            val accessoryList = accessoryLocalRepository.getAll()
            _favorites.postValue(getProductsType(coffeeList, accessoryList))
        }
    }

    private fun getProductsType(coffeeList: List<CoffeeEntity>?, accessoryList: List<AccessoryEntity>?): List<EntityType>? {
        val productList = mutableListOf<EntityType>()

        coffeeList?.forEach { coffee ->
            productList.add(coffee)
        }
        accessoryList?.forEach { accessory ->
            productList.add(accessory)
        }

        return productList
    }
}