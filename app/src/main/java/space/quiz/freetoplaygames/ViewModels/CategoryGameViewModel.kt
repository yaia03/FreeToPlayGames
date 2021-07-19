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

    fun getCategory(category: String): MutableLiveData<Response<List<Game>>>{
        loadCategory(category)

        return categoryResponse
    }

    private fun loadCategory(category: String){
        viewModelScope.launch {
            val response = repository.getCategory(category)
            categoryResponse.postValue(response)
        }
    }
}