package com.example.donny.labela.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.donny.labela.data.usecases.GetAllCharacters
import com.example.donny.labela.data.usecases.GetCharacterPage
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetAllCharactersTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getCharacterPage = Mockito.mock(GetCharacterPage::class.java)
    private val getAllCharacters = GetAllCharacters(getCharacterPage)

    @Test
    fun getFirstPage() {

    }

    @Test
    fun createJobs() {

    }

    @Test
    fun getOtherPages() {

    }

}