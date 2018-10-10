package com.example.donny.labela.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.donny.labela.R
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.ui.characterDetail.CharacterDetailActivity
import kotlinx.android.synthetic.main.character_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    private val adapter = ListAdapter()

    private val viewModel by viewModel<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list)

        initAdapter()
        initFetch()
    }

    private fun initAdapter() {
        val dividerDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView_character_list.layoutManager = LinearLayoutManager(this)
        recyclerView_character_list.addItemDecoration(dividerDecorator)
        recyclerView_character_list.adapter = adapter
        adapter.setListeners(this)
    }

    private fun initFetch() {
        viewModel.charObservable.observe(this, Observer {
            if (it?.data != null) {
                progressBar.visibility = View.GONE
                adapter.setList(it.data)
            } else {
                Toast.makeText(this, it?.error, Toast.LENGTH_LONG).show()
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
        if (char.name.equals("Luke Skywalker")) {
            overridePendingTransition(R.anim.slide_in_right_mark, R.anim.list_slide_out_left_mark)
        } else {
            overridePendingTransition(R.anim.slide_in_right, R.anim.list_slide_out_left)
        }
    }

}