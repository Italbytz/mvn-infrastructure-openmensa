package io.github.italbytz.infrastructure.openmensa

import retrofit2.Call
import retrofit2.http.*

/*
 * REST API of https://openmensa.org/api/v2
 */
interface OpenMensaAPI {

    @GET("canteens")
    fun getCanteens(): Call<List<Canteen>>

    @GET("canteens/{id}/days")
    fun getDays(@Path("id") id: Long): Call<List<Day>>

    @GET("canteens/{id}/days/{date}/meals")
    fun getMeals(@Path("id") id: Long, @Path("date") date: String): Call<List<Meal>>

}

