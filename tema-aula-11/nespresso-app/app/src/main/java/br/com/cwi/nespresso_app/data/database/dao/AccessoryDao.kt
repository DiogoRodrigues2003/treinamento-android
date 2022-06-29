package br.com.cwi.nespresso_app.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity

@Dao
interface AccessoryDao {

    @Insert
    fun add(accessoryEntity: AccessoryEntity)

    @Delete
    fun remove(accessoryEntity: AccessoryEntity)

    @Query("SELECT * FROM AccessoryEntity")
    fun getAll(): List<AccessoryEntity>?
}