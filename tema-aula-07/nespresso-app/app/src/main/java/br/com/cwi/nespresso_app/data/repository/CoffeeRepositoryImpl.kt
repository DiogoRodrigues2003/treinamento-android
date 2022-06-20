package br.com.cwi.nespresso_app.data.repository

import br.com.cwi.nespresso_app.data.mapper.AccessoryCategoryMapper
import br.com.cwi.nespresso_app.data.mapper.CategoryMapper
import br.com.cwi.nespresso_app.data.mapper.MachineMapper
import br.com.cwi.nespresso_app.data.network.NespressoApi
import br.com.cwi.nespresso_app.data.network.RetrofitConfig
import br.com.cwi.nespresso_app.domain.entity.AccessoryCategory
import br.com.cwi.nespresso_app.domain.entity.Category
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoffeeRepositoryImpl(
    private val categoryMapper: CategoryMapper,
    private val machineMapper: MachineMapper,
    private val accessoryCategoryMapper: AccessoryCategoryMapper
) : CoffeeRepository {

    private val api: NespressoApi = RetrofitConfig.service

    override suspend fun getCoffees(): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryMapper.toDomain(api.getCoffees())
        }
    }

    override suspend fun getMachines(): List<Machine> {
        return withContext(Dispatchers.IO) {
            machineMapper.toDomain(api.getMachines())
        }
    }

    override suspend fun getAccessories(): List<AccessoryCategory> {
        return withContext(Dispatchers.IO) {
            accessoryCategoryMapper.toDomain(api.getAccessories())
        }
    }
}