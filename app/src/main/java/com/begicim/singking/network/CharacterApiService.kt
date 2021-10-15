package com.begicim.singking.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CharacterApiService {
    @GET(NetworkConstants.BREAKING_BAD_CHARACTER_PATH)
    fun getCharacterList() : Deferred<List<CharacterModel>>
}