package com.example.donny.labela

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.character_row.view.*

class MainAdapter(val characters: List<Character>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.character_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val character = characters.get(position)
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