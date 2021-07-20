package space.quiz.freetoplaygames.Repository

import android.util.Log
import retrofit2.Response
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.Utils.RetrofitInstance

class Repository {

    suspend fun getGames(): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getGames().toString())
        return RetrofitInstance.api.getGames()
    }

    suspend fun getPcGames(): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getPcGames().toString())
        return RetrofitInstance.api.getPcGames()
    }

    suspend fun getBrowserGames(): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getBrowserGame().toString())
        return RetrofitInstance.api.getBrowserGame()
    }

    suspend fun getGame(id: Int): Response<Game>{
        Log.d("Response", RetrofitInstance.api.getGame(id).toString())
        return RetrofitInstance.api.getGame(id)
    }

    suspend fun getCategory(category: String): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getCategory(category).toString())
        return RetrofitInstance.api.getCategory(category)
    }

    suspend fun getPlatformAndCategory(platform: String, category: String): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getPlatformAndCategory(platform, category).toString())
        return RetrofitInstance.api.getPlatformAndCategory(platform, category)
    }

    suspend fun getPlatform(platform: String): Response<List<Game>>{
        Log.d("Response", RetrofitInstance.api.getPlatform(platform).toString())
        return RetrofitInstance.api.getPlatform(platform)
    }
}