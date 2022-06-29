package br.com.cwi.nespresso_app.presentation.feature.products.accessories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.nespresso_app.data.database.mapper.toEntity
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Coffee
import br.com.cwi.nespresso_app.domain.entity.Type
import br.com.cwi.nespresso_app.domain.repository.AccessoryLocalRepository
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import br.com.cwi.nespresso_app.presentation.base.BaseViewModel

class AccessoryViewModel(
    private val repository: CoffeeRepository,
    private val accessoryLocalRepository: AccessoryLocalRepository
) : BaseViewModel() {

    private val _accessories = MutableLiveData<List<Type>>()
    val accessories: LiveData<List<Type>> = _accessories

    private val _accessory = MutableLiveData<Accessory>()
    val accessory: LiveData<Accessory> = _accessory

    fun fetchAccessories() {
        launch {
            val categoryList = repository.getAccessories()
            _accessories.postValue(getAccessoriesType(categoryList))
        }
    }

    fun fetchAccessoryDetails(id: Int?) {
        launch {
            val selectedAccessory = repository.getAccessoryById(id)
            _accessory.postValue(selectedAccessory)
        }
    }

    private fun getAccessoriesType(categoryList: List<Category>): List<Type> {
        val typeList = mutableListOf<Type>()

        val favoriteList = accessoryLocalRepository.getAll()


        categoryList.forEach { category ->
            typeList.add(category)
            favoriteList?.takeIf { it.isNotEmpty() }?.let { favoriteList ->
                setIsAccessoryFavorite(favoriteList.map { it.id }, category.products.map { it as Accessory })
            }
            typeList.addAll(category.products)
        }

        return typeList
    }

    private fun setIsAccessoryFavorite(favoriteIdList: List<Int>, accessoryList: List<Accessory>) {
        accessoryList.forEach { accessory ->
            accessory.isFavorite = favoriteIdList.contains(accessory.id)
        }
    }

    fun setFavorite(accessory: Accessory) {
        val accessoryEntity = accessory.toEntity()
        if (accessory.isFavorite) accessoryLocalRepository.add(accessoryEntity)
        else accessoryLocalRepository.remove(accessoryEntity)
    }
}