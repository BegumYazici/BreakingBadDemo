package com.begicim.singking.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.begicim.singking.databinding.ItemCharacterBinding
import com.begicim.singking.ui.character.model.CharacterUIModel

class CharacterAdapter(
    private var characterList: MutableList<CharacterUIModel> = mutableListOf(),
    val clickListener: CharacterClickListener
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val initialCityDataList = ArrayList<CharacterUIModel>()

    fun updateList(characterList: MutableList<CharacterUIModel>) {
        initialCityDataList.clear()
        initialCityDataList.addAll(characterList)
        this.characterList = characterList
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterModel: CharacterUIModel) {
            binding.characterModel = characterModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val characterBinding = ItemCharacterBinding.inflate(layoutInflater, parent, false)

        return CharacterViewHolder(characterBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterModel = characterList[position]
        holder.bind(characterModel)

        holder.itemView.setOnClickListener {
            clickListener.onCharacterClicked(characterModel)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun getFilter(): Filter {
        return nameFilter
    }

    private val nameFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<CharacterUIModel> = ArrayList()

            if (constraint == null || constraint.isEmpty()) {
                initialCityDataList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim()

                initialCityDataList.filter {
                    it.name.contains(query, true)
                }.apply {
                    filteredList.addAll(this)
                }
            }

            return FilterResults().apply {
                this.values = filteredList
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                characterList.clear()
                characterList.addAll(results.values as List<CharacterUIModel>)
                notifyDataSetChanged()
            }
        }
    }
}