package com.example.donny.labela.utils

import com.example.donny.labela.data.models.Character

class CharacterNameComparator {

    companion object : Comparator<Character> {
        override fun compare(p0: Character, p1: Character): Int {
            return p0.name.compareTo(p1.name)
        }
    }

}