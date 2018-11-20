package com.example.donny.labela.data.models

class CharacterList(val results: List<Character>,
                    val count: Int,
                    val next: String) {

    companion object {
        fun empty() = CharacterList(emptyList(), 0, "")
    }

}