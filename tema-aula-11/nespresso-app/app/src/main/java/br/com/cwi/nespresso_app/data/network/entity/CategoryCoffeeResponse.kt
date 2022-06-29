package br.com.cwi.nespresso_app.data.network.entity

import com.squareup.moshi.Json

class CategoryCoffeeResponse(
    @Json(name = "categoria") val category: String,
    @Json(name = "cafes") val coffees: List<CoffeeResponse>
)