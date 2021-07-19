package space.quiz.freetoplaygames.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.Repository.Repository

class GameInfoViewModel(private val repository: Repository): ViewModel() {

    val gameResponse: MutableLiveData<Response<Game>> = MutableLiveData()

    fun getGame(id: Int): MutableLiveData<Response<Game>>{
        if (gameResponse.value == null)
            loadGame(id)

        return gameResponse
    }

    private fun loadGame(id: Int){
        viewModelScope.launch {
            val response = repository.getGame(id)
            gameResponse.postValue(response)
        }
    }
}