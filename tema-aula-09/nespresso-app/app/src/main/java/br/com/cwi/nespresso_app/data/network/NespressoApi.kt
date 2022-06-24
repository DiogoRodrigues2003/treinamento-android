package br.com.cwi.nespresso_app.data.network

import br.com.cwi.nespresso_app.data.entity.AccessoryResponse
import br.com.cwi.nespresso_app.data.entity.CategoryAccessoryResponse
import br.com.cwi.nespresso_app.data.entity.CategoryCoffeeResponse
import br.com.cwi.nespresso_app.data.entity.MachineResponse
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.domain.entity.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NespressoApi {

    @GET("/capsulas")
    suspend fun getCoffees(): List<CategoryCoffeeResponse>

    @GET("/maquinas")
    suspend fun getMachines(): List<MachineResponse>

    @GET("/acessorios")
    suspend fun getAccessories(): List<CategoryAccessoryResponse>

    @GET("/maquinas/{id}")
    suspend fun getMachineById(@Path("id") id: Int): MachineResponse

    @GET("/acessorios")
    suspend fun getAccessoryCategory(@Query("categoria") category: String): List<CategoryAccessoryResponse>
}