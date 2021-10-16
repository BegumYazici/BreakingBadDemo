package com.begicim.singking.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.begicim.singking.network.CharacterApiService
import com.begicim.singking.network.toListCharacterUIModel
import com.begicim.singking.ui.character.model.CharacterUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus {
    ERROR,
    LOADING,
    SUCCESS
}

class CharacterViewModel constructor(
    private val characterApiService: CharacterApiService,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _characterList = MutableLiveData<List<CharacterUIModel>>()
    val characterList: MutableLiveData<List<CharacterUIModel>> = _characterList

    private val job = Job()
    private val coroutineScope = CoroutineScope(dispatcher + job)

    fun getCharacters() {
        coroutineScope.launch {
            try {
                val characterList = characterApiService.getCharacterList()
                _status.value = ApiStatus.LOADING

                val serviceResponse = characterList.await()

                _characterList.value = serviceResponse.toListCharacterUIModel()
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
