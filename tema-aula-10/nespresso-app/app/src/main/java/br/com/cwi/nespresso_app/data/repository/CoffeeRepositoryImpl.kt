package br.com.cwi.nespresso_app.data.repository

import br.com.cwi.nespresso_app.data.network.mapper.CategoryAccessoryMapper
import br.com.cwi.nespresso_app.data.network.mapper.CategoryCoffeeMapper
import br.com.cwi.nespresso_app.data.network.mapper.MachineMapper
import br.com.cwi.nespresso_app.data.network.NespressoApi
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CoffeeRepositoryImpl(
    private val api: NespressoApi,
    private val categoryCoffeeMapper: CategoryCoffeeMapper,
    private val categoryAccessoryMapper: CategoryAccessoryMapper,
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
}