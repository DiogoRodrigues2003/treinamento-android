package br.com.cwi.nespresso_app.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity

@Dao
interface CoffeeDao {

    @Insert
    fun add(coffeeEntity: CoffeeEntity)

    @Delete
    fun remove(coffeeEntity: CoffeeEntity)

    @Query("SELECT * FROM CoffeeEntity")
    fun getAll(): List<CoffeeEntity>?
}