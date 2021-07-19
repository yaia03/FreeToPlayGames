package space.quiz.freetoplaygames.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.Repository.Repository

class AllGamesViewModel(private val repository: Repository): ViewModel() {
    var gamesResponse: MutableLiveData<Response<List<Game>>> = MutableLiveData()
    var pcGamesResponse: MutableLiveData<Response<List<Game>>> = MutableLiveData()
    var browserGamesResponse: MutableLiveData<Response<List<Game>>> = MutableLiveData()

    fun getGames(): MutableLiveData<Response<List<Game>>>{
        if (gamesResponse.value == null)
            loadGames()

        return gamesResponse
    }

    private fun loadGames(){
        viewModelScope.launch {
            val response = repository.getGames()
            gamesResponse.postValue(response)
        }
    }

    fun getPcGames(): MutableLiveData<Response<List<Game>>>{
        if (pcGamesResponse.value == null)
            loadPcGames()

        return pcGamesResponse
    }

    private fun loadPcGames(){
        viewModelScope.launch {
            val response = repository.getPcGames()
            pcGamesResponse.postValue(response)
        }
    }

    fun getBrowserGames(): MutableLiveData<Response<List<Game>>>{
        if (browserGamesResponse.value == null)
            loadBrowserGames()

        return browserGamesResponse
    }

    private fun loadBrowserGames(){
        viewModelScope.launch {
            val response = repository.getBrowserGames()
            browserGamesResponse.postValue(response)
        }
    }
}