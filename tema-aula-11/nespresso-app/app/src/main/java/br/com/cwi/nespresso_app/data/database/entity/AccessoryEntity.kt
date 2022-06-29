package br.com.cwi.nespresso_app.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccessoryEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val urlImage: String?,
    val unitPrice: Double,
    val description: String?,
) : EntityType(ProductType.ACCESSORY)
