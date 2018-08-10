package com.example.donny.labela.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.donny.labela.R
import com.example.donny.labela.app.App
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.ui.characterDetail.CharacterDetailActivity
import com.example.donny.labela.ui.list.di.DaggerListComponent
import com.example.donny.labela.ui.list.di.ListModule
import com.example.donny.labela.ui.list.di.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    private val adapter = ListAdapter()

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerListComponent.builder()
                .applicationComponent(App.get(this).applicationComponent)
                .listModule(ListModule())
                .build().inject(this)
        val vm = ViewModelProviders.of(this, viewModelFactory)[ListViewModel::class.java]

        initAdapter()
        initFetch(vm)


    }

    private fun initAdapter() {

        val dividerDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.addItemDecoration(dividerDecorator)
        recyclerView_main.adapter = adapter
        adapter.setListeners(this)
    }

    private fun initFetch(vm: ListViewModel) {
        vm.observable.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
            }
        })

        vm.fetchData()
    }

    override fun onItemClick(char: Character) {
        val intent = CharacterDetailActivity.getIntent(this)
        intent.putExtra("char_id", char.name)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.list_slide_out_left)
    }

}