package com.example.donny.labela.ui.characterDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.donny.labela.R
import com.example.donny.labela.data.models.Character
import kotlinx.android.synthetic.main.character_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterDetailViewModel>()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CharacterDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val character = intent.getParcelableExtra<Character>("selectedCharacter")
        if (character.name == "Luke Skywalker") {
            setContentView(R.layout.mark)
        } else {
            setContentView(R.layout.character_details)
            supportActionBar?.title = character.name
            fillAdapter(character)
        }
        getHomeworld(character)
    }



    private fun fillAdapter(character: Character) {
        textView_name_right.text = character.name
        textView_height_right.text = character.height
        textView_mass_right.text = character.mass
        textView_hair_color_right.text = character.hair_color
        textView_skin_color_right.text = character.skin_color
        textView_eye_color_right.text = character.eye_color
        textView_birth_year_right.text = character.birth_year
        textView_gender_right.text = character.gender
    }

    private fun getHomeworld(character: Character) {
        viewModel.planetObservable.observe(this, Observer {
            textView_homeworld_right.text = it?.name
        })
        Regex("""\d+""").find(character.homeworld)?.let {
            viewModel.getPlanet(it.value)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.list_slide_in_left, R.anim.slide_out_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}