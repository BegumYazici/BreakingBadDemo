package com.begicim.singking.ui.detail

import com.begicim.singking.ui.character.model.CharacterUIModel

interface DetailClickListener {
    fun onDetailClicked(model: CharacterUIModel)
}