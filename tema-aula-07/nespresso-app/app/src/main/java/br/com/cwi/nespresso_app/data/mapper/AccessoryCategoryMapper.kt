package br.com.cwi.nespresso_app.data.mapper

import br.com.cwi.nespresso_app.data.entity.AccessoryCategoryResponse
import br.com.cwi.nespresso_app.domain.entity.AccessoryCategory

class AccessoryCategoryMapper: DomainMapper<AccessoryCategoryResponse, AccessoryCategory> {
    override fun toDomain(from: AccessoryCategoryResponse) = AccessoryCategory(
        category = from.category,
        accessories = AccessoryMapper().toDomain(from.accessories)
    )

    override fun toDomain(from: List<AccessoryCategoryResponse>) = from.map {
        toDomain(it)
    }
}