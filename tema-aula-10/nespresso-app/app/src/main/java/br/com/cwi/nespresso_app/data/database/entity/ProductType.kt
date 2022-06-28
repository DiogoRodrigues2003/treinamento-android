package br.com.cwi.nespresso_app.data.database.entity

enum class ProductType(val value: Int) {
    COFFEE(value = 0),
    MACHINE(value = 1),
    ACCESSORY(value = 2)
}