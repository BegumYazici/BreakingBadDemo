package com.begicim.singking.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.begicim.singking.R
import com.begicim.singking.databinding.FragmentDetailCharacterBinding
import com.begicim.singking.network.CharacterModel
import com.begicim.singking.ui.MainActivity.Companion.KEY_CHARACTER_MODEL
import com.begicim.singking.ui.character.model.CharacterUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var detailDataBinding : FragmentDetailCharacterBinding

    private lateinit var name : String
    private lateinit var birthday : String
    private lateinit var portrayed : String
    private lateinit var characterModel : CharacterUIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            characterModel = it.getSerializable(KEY_CHARACTER_MODEL) as CharacterUIModel

            name = characterModel.name
            birthday = characterModel.birthday
            portrayed = characterModel.portrayed
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_character, container, false)
        detailDataBinding.lifecycleOwner = viewLifecycleOwner
        detailDataBinding.characterModel = characterModel

        return detailDataBinding.root
    }
}