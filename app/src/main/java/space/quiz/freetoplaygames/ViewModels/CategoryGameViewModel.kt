package space.quiz.freetoplaygames.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.Repository.Repository

class CategoryGameViewModel(private val repository: Repository): ViewModel() {
    var categoryResponse: MutableLiveData<Response<List<Game>>> = MutableLiveData()
    var categoryPosition: MutableLiveData<String> = MutableLiveData()

    fun getCategory(category: String?): MutableLiveData<Response<List<Game>>>{
        if (category != null)
            loadCategory(category)
        else
            loadAllGames()

        return categoryResponse
    }
//
//    fun getPlatform(platform: String): MutableLiveData<Response<List<Game>>>{
//
//    }

    fun loadCategory(category: String){
        viewModelScope.launch {
            val response = repository.getCategory(category)
            categoryResponse.postValue(response)
        }
    }

    private fun loadAllGames(){
        viewModelScope.launch {
            val response = repository.getGames()
            categoryResponse.postValue(response)
        }
    }

    private fun loadPlatform(platform: String){
        viewModelScope.launch {
            val response = repository.getPlatform(platform)
            categoryResponse.postValue(response)
        }
    }

    private fun loadPlatformAndCategory(platform: String, category: String){
        viewModelScope.launch {
            val response = repository.getPlatformAndCategory(platform, category)
            categoryResponse.postValue(response)
        }
    }

}