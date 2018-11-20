package com.example.donny.labela.ui.characterList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.donny.labela.R
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.ui.characterDetail.CharacterDetailActivity
import kotlinx.android.synthetic.main.character_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterListActivity : AppCompatActivity(), CharacterListAdapter.OnItemClickListener {

    private val adapter = CharacterListAdapter()

    private val viewModel by viewModel<CharacterListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list)

        initAdapter()
        initFetch()
    }

    private fun initAdapter() {
        val dividerDecorator = androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        recyclerView_character_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView_character_list.addItemDecoration(dividerDecorator)
        recyclerView_character_list.adapter = adapter
        adapter.setListeners(this)
    }

    private fun initFetch() {
        viewModel.characterList.observe(this, Observer {
            if (it != null) {
                progressBar.visibility = View.GONE
                adapter.setList(it)
            } else {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.getAllCharacters()
        progressBar.visibility = View.VISIBLE
    }

    override fun onItemClick(char: Character) {
        val intent = CharacterDetailActivity.getIntent(this)
        intent.putExtra("selectedCharacter", char)
        startActivity(intent)
        if (char.name == "Luke Skywalker") {
            overridePendingTransition(R.anim.slide_in_right_mark, R.anim.list_slide_out_left_mark)
        } else {
            overridePendingTransition(R.anim.slide_in_right, R.anim.list_slide_out_left)
        }
    }

}