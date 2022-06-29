package br.com.cwi.nespresso_app.domain.repository

import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity

interface AccessoryLocalRepository {
    fun add(accessoryEntity: AccessoryEntity)
    fun remove(accessoryEntity: AccessoryEntity)
    fun getAll(): List<AccessoryEntity>?
}