package space.quiz.freetoplaygames.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.quiz.freetoplaygames.Repository.Repository

class CategoryGameViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryGameViewModel(repository) as T
    }
}