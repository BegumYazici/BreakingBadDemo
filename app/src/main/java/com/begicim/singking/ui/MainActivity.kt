package com.begicim.singking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.begicim.singking.R
import com.begicim.singking.ui.character.CharacterFragment
import com.begicim.singking.ui.character.model.CharacterUIModel
import com.begicim.singking.ui.detail.DetailClickListener
import com.begicim.singking.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterFragment: CharacterFragment =
            if (savedInstanceState == null) {
                val fragment = CharacterFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameContainerView, fragment, CharacterFragment.TAG)
                    .commit()

                fragment
            } else {
                supportFragmentManager.findFragmentByTag(CharacterFragment.TAG) as CharacterFragment
            }

        characterFragment.detailClickListener = object : DetailClickListener {
            override fun onDetailClicked(model: CharacterUIModel) {
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