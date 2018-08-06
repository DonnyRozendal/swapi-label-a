package com.example.donny.labela.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.donny.labela.R
import com.example.donny.labela.ui.list.di.DaggerListComponent
import com.example.donny.labela.ui.list.di.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    private val adapter = ListAdapter()

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerListComponent.create().inject(this)
        val vm = ViewModelProviders.of(this, viewModelFactory)[ListViewModel::class.java]

        initAdapter()
        initFetch(vm)
    }

    private fun initAdapter() {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = adapter
    }

    private fun initFetch(vm: ListViewModel) {
        vm.observable.observe(this, Observer {
            if (it != null) { adapter.setList(it) }
        })
    }

}