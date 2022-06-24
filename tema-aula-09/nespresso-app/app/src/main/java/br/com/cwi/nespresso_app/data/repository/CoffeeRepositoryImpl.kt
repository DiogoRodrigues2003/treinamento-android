package br.com.cwi.nespresso_app.data.repository

import br.com.cwi.nespresso_app.data.entity.AccessoryResponse
import br.com.cwi.nespresso_app.data.entity.CategoryAccessoryResponse
import br.com.cwi.nespresso_app.data.mapper.AccessoryMapper
import br.com.cwi.nespresso_app.data.mapper.CategoryAccessoryMapper
import br.com.cwi.nespresso_app.data.mapper.CategoryCoffeeMapper
import br.com.cwi.nespresso_app.data.mapper.MachineMapper
import br.com.cwi.nespresso_app.data.network.NespressoApi
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.domain.entity.Product
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoffeeRepositoryImpl(
    private val api: NespressoApi,
    private val categoryCoffeeMapper: CategoryCoffeeMapper,
    private val categoryAccessoryMapper: CategoryAccessoryMapper,
    private val accessoryMapper: AccessoryMapper,
    private val machineMapper: MachineMapper
) : CoffeeRepository {

    override suspend fun getCoffees(): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryCoffeeMapper.toDomain(api.getCoffees())
        }
    }

    override suspend fun getMachines(): List<Machine> {
        return withContext(Dispatchers.IO) {
            machineMapper.toDomain(api.getMachines())
        }
    }

    override suspend fun getAccessories(): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryAccessoryMapper.toDomain(api.getAccessories())
        }

    }
    override suspend fun getMachineById(id: Int): Machine? {
        return withContext(Dispatchers.IO) {
            machineMapper.toDomain(api.getMachineById(id))
        }
    }

    override suspend fun getAccessoryDetails(itemId: Int, category: String): Accessory? {
        return withContext(Dispatchers.IO) {
            val accessoryCategory: CategoryAccessoryResponse = api.getAccessoryCategory(category).first()
            val accessory: AccessoryResponse? =
                accessoryCategory.accessories.find { it.id == itemId }
            accessoryMapper.toDomain(accessory, category)
        }
    }
}