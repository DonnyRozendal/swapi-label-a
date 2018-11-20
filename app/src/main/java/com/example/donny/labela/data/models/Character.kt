package com.example.donny.labela.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Character(val name: String,
                val height: String,
                val mass: String,
                val hair_color: String,
                val skin_color: String,
                val eye_color: String,
                val birth_year: String,
                val gender: String,
                val homeworld: String,
                val films: List<String>,
                val species: List<String>,
                val vehicles: List<String>,
                val starships: List<String>,
                val created: String,
                val edited: String,
                val url: String) : Parcelable {

    companion object {
        fun empty() = Character("",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                "",
                "",
                "")
    }

}