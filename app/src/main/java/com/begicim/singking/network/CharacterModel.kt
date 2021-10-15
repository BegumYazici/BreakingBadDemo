package com.begicim.singking.network

import com.begicim.singking.ui.character.model.CharacterUIModel
import com.squareup.moshi.Json

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
    val characterUIList = mutableListOf<CharacterUIModel>()

    for (i in this) {
        characterUIList.add(i.toCharacterUIModel())
    }
    return characterUIList
}