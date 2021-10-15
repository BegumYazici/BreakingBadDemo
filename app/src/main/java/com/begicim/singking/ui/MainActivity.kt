package com.begicim.singking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.begicim.singking.R
import com.begicim.singking.network.CharacterModel
import com.begicim.singking.ui.character.CharacterFragment
import com.begicim.singking.ui.character.model.CharacterUIModel
import com.begicim.singking.ui.detail.DetailFragment
import com.begicim.singking.ui.detail.DetailClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterFragment = CharacterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainerView, characterFragment)
            .commit()

        characterFragment.detailClickListener = object : DetailClickListener {
            override fun detailClickListener(model: CharacterUIModel) {
                val detailFragment = DetailFragment()

                val bundle = Bundle()
                bundle.putSerializable(KEY_CHARACTER_MODEL, model)

                detailFragment.arguments = bundle

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameContainerView, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    companion object {
        const val KEY_CHARACTER_MODEL = "CharacterModel"
    }
}