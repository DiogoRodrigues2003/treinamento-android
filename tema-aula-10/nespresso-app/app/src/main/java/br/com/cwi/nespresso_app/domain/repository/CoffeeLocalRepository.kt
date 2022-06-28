package br.com.cwi.nespresso_app.domain.repository

import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity

interface CoffeeLocalRepository {
    fun add(coffeeEntity: CoffeeEntity)
    fun remove(coffeeEntity: CoffeeEntity)
    fun getAll(): List<CoffeeEntity>?
}