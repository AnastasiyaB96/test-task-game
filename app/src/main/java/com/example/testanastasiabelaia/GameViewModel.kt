package com.example.testanastasiabelaia

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
    var job: Job? = null

    fun getShowGame() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError("Ошибка запроса: ${throwable.localizedMessage}")
        }
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = GameRepository.getShowGame()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body()?.args?.key == "showGame") {
                        showGame.postValue(LoadingResult.SHOW_GAME)
                    } else {
                        showGame.postValue(LoadingResult.SHOW_WEB_VIEW)
                    }
                    loading.value = false
                } else {
                    onError(response.message())
                }
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