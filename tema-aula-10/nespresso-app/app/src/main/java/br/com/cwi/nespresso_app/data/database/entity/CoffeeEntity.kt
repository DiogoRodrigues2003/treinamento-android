package br.com.cwi.nespresso_app.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.cwi.nespresso_app.data.database.MeasuresConverter

@Entity
data class CoffeeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val urlImage: String?,
    val unitPrice: Double,
    val description: String,
    val intensity: Int?,
    @field:TypeConverters(MeasuresConverter::class) val measures: List<String>
) : EntityType(ProductType.COFFEE)