package br.com.cwi.nespresso_app.data.database.mapper

import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity
import br.com.cwi.nespresso_app.domain.entity.Accessory

fun Accessory.toEntity() = AccessoryEntity (
    id, name, urlImage, unitPrice, description
)