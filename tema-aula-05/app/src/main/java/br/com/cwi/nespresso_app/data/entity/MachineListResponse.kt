package br.com.cwi.nespresso_app.data.entity

import com.squareup.moshi.Json

data class MachineListResponse (
    @Json(name = "maquinas") val machines: List<MachineResponse>?
)