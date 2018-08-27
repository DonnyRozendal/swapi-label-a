package com.example.donny.labela.ui.characterDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.donny.labela.R
import com.example.donny.labela.data.models.Character
import kotlinx.android.synthetic.main.character_details.*
import net.idik.lib.slimadapter.SlimAdapter
import org.koin.android.viewmodel.ext.android.viewModel

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
        if (character.name.equals("Luke Skywalker")) {
            setContentView(R.layout.mark)
        } else {
            setContentView(R.layout.character_details)
            supportActionBar?.title = character.name
            fillAdapter(character)
        }

    }

    private fun fillAdapter(character: Character) {
        //textView_name_right.text = character.name

        recyclerView_character_details.layoutManager = LinearLayoutManager(this)
        SlimAdapter.create()
                .register<Character>(R.layout.character_details_row) { data, injector ->
                    injector.text(R.id.textView_name_right, data.name)
                            .text(R.id.textView_height_right, data.height)
                            .text(R.id.textView_mass_right, data.mass)
                            .text(R.id.textView_hair_color_right, data.hair_color)
                            .text(R.id.textView_skin_color_right, data.skin_color)
                            .text(R.id.textView_eye_color_right, data.eye_color)
                            .text(R.id.textView_birth_year_right, data.birth_year)
                            .text(R.id.textView_gender_right, data.gender)
                }
                .attachTo(recyclerView_character_details)
                .updateData(listOf(character))
        val planetUrl = character.homeworld
        val test = Regex("""\d+""").find("https://swapi.co/api/planets/1/")?.value
        println(planetUrl)
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