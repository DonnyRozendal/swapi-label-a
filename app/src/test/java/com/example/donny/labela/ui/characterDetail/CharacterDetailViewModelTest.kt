package com.example.donny.labela.ui.characterDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.models.Planet
import com.example.donny.labela.data.usecases.GetCharacterDetails
import com.example.donny.labela.utils.any
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

class CharacterDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getCharacterDetails = mock(GetCharacterDetails::class.java)
    private val characterDetailViewModel = CharacterDetailViewModel(getCharacterDetails)

    @Test
    fun fetchPlanetUpdateLiveData() {
        // Arrange
        val planet = Planet("Earth")
        `when`(runBlocking { getCharacterDetails.run(any()) }).thenReturn(Either.Right(planet))

        // Act
        characterDetailViewModel.getPlanet("0")

        // Verify
        characterDetailViewModel.planet.observeForever {
            assertEquals(it.name, "Earth")
        }

    }

}