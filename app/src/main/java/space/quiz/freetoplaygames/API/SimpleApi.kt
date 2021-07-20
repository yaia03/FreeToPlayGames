package space.quiz.freetoplaygames.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import space.quiz.freetoplaygames.Models.Game

interface SimpleApi {

    @GET("/api/games")
    suspend fun getGames(): Response<List<Game>>

    @GET("api/games?platform=pc")
    suspend fun getPcGames(): Response<List<Game>>

    @GET("api/games?platform=browser")
    suspend fun getBrowserGame(): Response<List<Game>>

    @GET("api/game?")
    suspend fun getGame(@Query("id") id: Int): Response<Game>

    @GET("api/games?")
    suspend fun getCategory(@Query("category") category: String): Response<List<Game>>

    @GET("api/games?")
    suspend fun getPlatformAndCategory(@Query("platform") platform: String,
                                       @Query("category") category: String): Response<List<Game>>

    @GET("api/games?")
    suspend fun getPlatform(@Query("platform") platform: String): Response<List<Game>>

}