package com.example.donny.labela.di.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * Created by donnyrozendal on 27-08-18.
 */
@Dao
interface CharacterDao {

    @Query("SELECT * from CharacterData")
    fun getAll(): List<CharacterData>

    @Insert(onConflict = REPLACE)
    fun isert(characterData: CharacterData)

    @Query("DELETE from characterData")
    fun deleteAll()

}