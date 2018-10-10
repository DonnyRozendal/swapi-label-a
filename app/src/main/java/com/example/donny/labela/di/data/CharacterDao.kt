package com.example.donny.labela.di.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

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