package com.example.donny.labela.ui.list

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donny.labela.R
import com.example.donny.labela.ui.characterDetail.CharacterDetailActivity
import kotlinx.android.synthetic.main.character_row.view.*
import com.example.donny.labela.data.models.Character

class ListAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val itemList = mutableListOf<Character>()

    fun setList(newList: List<Character>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.character_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val character = itemList.get(position)
        holder.view.textView_character_name.text = character.name
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CharacterDetailActivity::class.java)
            view.context.startActivity(intent)
        }
    }

}