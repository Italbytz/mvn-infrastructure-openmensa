package io.github.italbytz.infrastructure.openmensa

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenMensaService : OpenMensaAPI {

    val retrofit =
        Retrofit.Builder().baseUrl("https://openmensa.org/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service: OpenMensaAPI =
        retrofit.create(OpenMensaAPI::class.java)

    override fun getCanteens(): Call<List<Canteen>> = service.getCanteens()

    override fun getDays(id: Long): Call<List<Day>> = service.getDays(id)

    override fun getMeals(id: Long, date: String): Call<List<Meal>> =
        service.getMeals(id,date)
}