package br.com.cwi.nespresso_app.domain.repository

import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.domain.entity.Product

interface CoffeeRepository {
    suspend fun getCoffees(): List<Category>
    suspend fun getMachines(): List<Machine>
    suspend fun getAccessories(): List<Category>
    suspend fun getMachineById(id: Int): Machine?
    suspend fun getAccessoryDetails(itemId: Int, category: String): Accessory?
}