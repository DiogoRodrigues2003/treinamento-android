package br.com.cwi.nespresso_app.data.network

import br.com.cwi.nespresso_app.data.network.entity.AccessoryResponse
import br.com.cwi.nespresso_app.data.network.entity.CategoryAccessoryResponse
import br.com.cwi.nespresso_app.data.network.entity.CategoryCoffeeResponse
import br.com.cwi.nespresso_app.data.network.entity.MachineResponse
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

    @GET("/acessorios-individuais/{id}")
    suspend fun getAccessoryById(@Path("id") id: Int?): AccessoryResponse
}