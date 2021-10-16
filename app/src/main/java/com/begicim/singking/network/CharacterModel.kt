package com.begicim.singking.network

import androidx.annotation.Keep
import com.begicim.singking.ui.character.model.CharacterUIModel
import com.squareup.moshi.Json

@Keep
data class CharacterModel(
    @field:Json(name = "char_id")
    val id: Int,
    val name: String,
    val birthday: String,
    val img: String,
    val nickname: String,
    val portrayed: String
)

fun CharacterModel.toCharacterUIModel(): CharacterUIModel {
    return CharacterUIModel(id, name, birthday, img, nickname, portrayed)
}

fun List<CharacterModel>.toListCharacterUIModel(): List<CharacterUIModel> {
    return this.map { characterModel ->
        characterModel.toCharacterUIModel()
    }
}
