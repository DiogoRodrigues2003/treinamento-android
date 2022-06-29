package br.com.cwi.nespresso_app.data.database.entity

import androidx.room.TypeConverters
import br.com.cwi.nespresso_app.data.database.ProductTypeConverter

sealed class EntityType(
    @field:TypeConverters(ProductTypeConverter::class) var type: ProductType
)
