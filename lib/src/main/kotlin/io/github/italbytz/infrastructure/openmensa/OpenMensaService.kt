package io.github.italbytz.infrastructure.openmensa

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoMealsForDateException(message: String) : Exception(message) {}
class MensaClosedException(message: String) : Exception(message) {}

class OpenMensaService : OpenMensaAPI {

    val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val retrofit =
        Retrofit.Builder().baseUrl("https://openmensa.org/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service: OpenMensaAPI =
        retrofit.create(OpenMensaAPI::class.java)

    override fun getCanteens(): Call<List<Canteen>> = service.getCanteens()

    override fun getDays(id: Long): Call<List<Day>> = service.getDays(id)

    override fun getMeals(id: Long, date: String): Call<List<Meal>> {
        val days = getDays(id).execute().body()!!
        for (day in days) {
            if (day.date.equals(date)) {
                if (day.closed) {
                    throw MensaClosedException("Mensa $id closed on $date.")
                }
                return service.getMeals(id, date)
            }
        }
        throw NoMealsForDateException("No meals for Mensa $id on $date.")
    }

    fun getTodaysMeals(id: Long): Call<List<Meal>> {
        val formattedDate = java.time.LocalDate.now().format(formatter)
        return getMeals(id, formattedDate)
    }
}