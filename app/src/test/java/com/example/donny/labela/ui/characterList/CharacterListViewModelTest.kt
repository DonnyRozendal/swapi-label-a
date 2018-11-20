package com.example.donny.labela.ui.characterList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.usecases.GetAllCharacters
import com.example.donny.labela.utils.any
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class CharacterListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getAllCharacters = Mockito.mock(GetAllCharacters::class.java)
    private val characterListViewModel = CharacterListViewModel(getAllCharacters)

    @Test
    fun updateLiveData() {
        // Arrange
        val characterList = listOf(Character.empty())
        `when`(runBlocking { getAllCharacters.run(any()) }).thenReturn(Either.Right(characterList))

        // Act
        characterListViewModel.getAllCharacters()

        // Verify
        characterListViewModel.characterList.observeForever {
            assertEquals(it[0].name, "")
        }
    }

}