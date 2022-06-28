package br.com.cwi.nespresso_app.data.database.mapper

import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity
import br.com.cwi.nespresso_app.domain.entity.Coffee

fun Coffee.toEntity() = CoffeeEntity (
    id, name, urlImage, unitPrice, description, intensity, measures
)