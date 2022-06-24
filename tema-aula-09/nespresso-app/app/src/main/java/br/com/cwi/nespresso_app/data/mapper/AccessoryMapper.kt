package br.com.cwi.nespresso_app.data.mapper

import br.com.cwi.nespresso_app.data.entity.AccessoryResponse
import br.com.cwi.nespresso_app.domain.entity.Accessory

class AccessoryMapper {

    fun toDomain(from: AccessoryResponse?, accCategory: String) = Accessory(
        id = from?.id,
        name = from?.name,
        description = from?.description,
        price = from?.price,
        urlImage = from?.urlImage,
        category = accCategory
    )


    fun toDomain(from: List<AccessoryResponse>, accCategory: String) = from.map {
        toDomain(it, accCategory)
    }
}