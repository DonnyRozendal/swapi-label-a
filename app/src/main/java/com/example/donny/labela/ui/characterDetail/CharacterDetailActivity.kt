package com.example.donny.labela.ui.characterDetail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.donny.labela.R
import kotlinx.android.synthetic.main.activity_main.*

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CharacterDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details)
        //supportActionBar?.title = "Character details"

//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        window.enterTransition = null

        //recyclerView_main.layoutManager = LinearLayoutManager(this)
        //recyclerView_main.adapter = ??

        val charID = intent.getStringExtra("char_id").orEmpty()


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.list_slide_in_left, R.anim.slide_out_right)

    }

}