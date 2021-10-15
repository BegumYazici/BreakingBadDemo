package com.begicim.singking.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.begicim.singking.databinding.FragmentCharacterBinding
import com.begicim.singking.ui.character.adapter.CharacterAdapter
import com.begicim.singking.ui.character.adapter.CharacterClickListener
import com.begicim.singking.ui.character.model.CharacterUIModel
import com.begicim.singking.ui.detail.DetailClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    @Inject
    lateinit var characterViewModelFactory: CharacterViewModelFactory

    private lateinit var characterViewModel: CharacterViewModel

    private lateinit var characterBinding: FragmentCharacterBinding

    var detailClickListener: DetailClickListener? = null

    private val characterAdapter =
        CharacterAdapter(clickListener = object : CharacterClickListener {
            override fun onCharacterClicked(character: CharacterUIModel) {
                detailClickListener?.detailClickListener(character)
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterViewModel =
            ViewModelProvider(this, characterViewModelFactory).get(CharacterViewModel::class.java)

        characterViewModel.getCharacters()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterBinding = FragmentCharacterBinding.inflate(inflater)
        characterBinding.lifecycleOwner = viewLifecycleOwner
        characterBinding.characterViewModel = characterViewModel

        characterBinding.characterListRecyclerview.adapter = characterAdapter

        characterViewModel.characterList.observe(viewLifecycleOwner, {
            characterAdapter.updateList(ArrayList(it))
            characterAdapter.notifyDataSetChanged()
        })

        characterBinding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                characterAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                characterAdapter.getFilter().filter(newText)
                return true
            }

        })

        return characterBinding.root
    }

}