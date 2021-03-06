package br.com.cwi.nespresso_app.presentation.feature.products.coffee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.nespresso_app.data.database.mapper.toEntity
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Coffee
import br.com.cwi.nespresso_app.domain.entity.Type
import br.com.cwi.nespresso_app.domain.repository.CoffeeLocalRepository
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import br.com.cwi.nespresso_app.presentation.base.BaseViewModel

class CoffeeViewModel(
    private val coffeeRepository: CoffeeRepository,
    private val coffeeLocalRepository: CoffeeLocalRepository
) : BaseViewModel() {

    private val _coffees = MutableLiveData<List<Type>>()
    val coffees: LiveData<List<Type>> = _coffees

    fun fetchCoffees() {
        launch {
            val categoryList = coffeeRepository.getCoffees()
            _coffees.postValue(getCoffeesType(categoryList))
        }
    }

    private fun getCoffeesType(categoryList: List<Category>): List<Type> {
        val coffeeList = mutableListOf<Type>()

        val favoriteList = coffeeLocalRepository.getAll()

        categoryList.forEach { category ->
            coffeeList.add(category)
            favoriteList?.takeIf { it.isNotEmpty() }?.let { favoriteList ->
                setIsCoffeeFavorite(favoriteList.map { it.id }, category.products.map { it as Coffee })
            }
            coffeeList.addAll(category.products)
        }
        return coffeeList
    }

    private fun setIsCoffeeFavorite(favoriteIdList: List<Int>, coffeeList: List<Coffee>) {
        coffeeList.forEach { coffee ->
            coffee.isFavorite = favoriteIdList.contains(coffee.id)
        }
    }

    fun setFavorite(coffee: Coffee) {
        val coffeeEntity = coffee.toEntity()
        if (coffee.isFavorite) coffeeLocalRepository.add(coffeeEntity)
        else coffeeLocalRepository.remove(coffeeEntity)
    }

}