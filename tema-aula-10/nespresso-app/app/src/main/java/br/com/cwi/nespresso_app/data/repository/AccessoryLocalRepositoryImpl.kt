package br.com.cwi.nespresso_app.data.repository

import br.com.cwi.nespresso_app.data.database.AppDatabase
import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity
import br.com.cwi.nespresso_app.domain.repository.AccessoryLocalRepository

class AccessoryLocalRepositoryImpl (
    appDatabase: AppDatabase
) : AccessoryLocalRepository {

    private val dao = appDatabase.getAccessoryDao()

    override fun add(accessoryEntity: AccessoryEntity) {
        dao.add(accessoryEntity)
    }

    override fun remove(accessoryEntity: AccessoryEntity) {
        dao.remove(accessoryEntity)
    }

    override fun getAll(): List<AccessoryEntity>? = dao.getAll()
}
