package com.example.donny.labela.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donny.labela.R
import com.example.donny.labela.data.models.Character
import kotlinx.android.synthetic.main.character_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.CustomViewHolder>() {

    private val itemList = mutableListOf<Character>()

    private var listener : OnItemClickListener? = null

    fun setListeners(listener: OnItemClickListener?) {
        this.listener = listener
    }

    fun setList(newList: List<Character>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.character_row, parent, false)
        return CustomViewHolder(cellForRow, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val character = itemList.get(position)
        holder.bind(character)
    }

    inner class CustomViewHolder(val view: View, listener: OnItemClickListener?): RecyclerView.ViewHolder(view) {

        fun bind(char: Character) {
            view.textView_character_name.text = char.name
            view.setOnClickListener{
                listener?.onItemClick(char)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(char: Character)
    }
}

