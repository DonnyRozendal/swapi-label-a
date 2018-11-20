package com.example.donny.labela.di.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by donnyrozendal on 27-08-18.
 */
@Entity(tableName = "CharacterData")
class CharacterData(@PrimaryKey var name: String,
                    @ColumnInfo(name = "height") var height: String,
                    @ColumnInfo(name = "mass") var mass: String,
                    @ColumnInfo(name = "hair_color") var hair_color: String,
                    @ColumnInfo(name = "skin_color") var skin_color: String,
                    @ColumnInfo(name = "eye_color") var eye_color: String,
                    @ColumnInfo(name = "birth_year") var birth_year: String,
                    @ColumnInfo(name = "gender") var gender: String,
                    @ColumnInfo(name = "homeworld") var homeworld: String
) {

    constructor():this("","","","","","","","","")

}