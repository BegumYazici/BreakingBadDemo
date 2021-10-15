package com.begicim.singking.ui.character.model

import java.io.Serializable

data class CharacterUIModel(
    val id: Int,
    val name: String,
    val birthday: String,
    val img: String,
    val nickname: String,
    val portrayed: String
) : Serializable