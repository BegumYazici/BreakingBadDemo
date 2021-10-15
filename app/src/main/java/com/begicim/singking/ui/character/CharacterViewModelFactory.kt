package com.begicim.singking.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.begicim.singking.network.CharacterApiService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CharacterViewModelFactory @Inject constructor(
    private val characterApiService: CharacterApiService,
    private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CharacterViewModel::class.java) {
            return CharacterViewModel(characterApiService, dispatcher) as T
        }

        throw IllegalArgumentException("$modelClass is not supported")
    }
}
