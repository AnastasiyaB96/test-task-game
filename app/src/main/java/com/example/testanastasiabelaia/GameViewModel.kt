package com.example.testanastasiabelaia

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testanastasiabelaia.data.GameRepository
import com.example.testanastasiabelaia.models.ResponseModel
import kotlinx.coroutines.*
import retrofit2.Response

class GameViewModel : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    var showGame: MutableLiveData<LoadingResult> = MutableLiveData(LoadingResult.WAITING)
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    fun getShowGame(preferences: SharedPreferences) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError("Ошибка запроса: ${throwable.localizedMessage}")
        }
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = GameRepository.getShowGame(preferences)
            withContext(Dispatchers.Main) {
                when(result){
                    LoadingResult.SHOW_GAME,LoadingResult.SHOW_WEB_VIEW -> showGame.postValue(result)
                    LoadingResult.ERROR-> errorMessage.postValue("Error in getting data. Check logs for more info.")
                    else -> {}
                }
                loading.value = false
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}