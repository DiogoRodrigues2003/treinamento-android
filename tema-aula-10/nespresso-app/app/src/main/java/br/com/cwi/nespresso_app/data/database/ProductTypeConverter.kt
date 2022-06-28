package br.com.cwi.nespresso_app.data.database

import androidx.room.TypeConverter
import br.com.cwi.nespresso_app.data.database.entity.ProductType

class ProductTypeConverter {

    @TypeConverter
    fun toEnum(value: String) = enumValueOf<ProductType>(value)

    @TypeConverter
    fun fromEnum(value: ProductType) = value.name
}