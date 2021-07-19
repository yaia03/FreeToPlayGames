package space.quiz.freetoplaygames.Utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.quiz.freetoplaygames.API.SimpleApi

object RetrofitInstance {

    const val BASE_URL = "https://www.freetogame.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}