package com.doubleg.pokedex

import com.doubleg.pokedex.domain.PokemonUseCase
import com.doubleg.pokedex.domain.model.PokemonView
import com.doubleg.pokedex.repository.Repository
import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.repository.model.Sprites
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PokemonUseCaseTest {

    private val pokemonViewList =
        listOf(
            PokemonView("pikachu", "url"),
            PokemonView("charmander", "url2")
        )

    private val pokemonList =
        listOf(
            Pokemon("pikachu", 1, Sprites("url")),
            Pokemon("charmander", 2, Sprites("url2"))
        )

    private val mockedRepository = mock(Repository::class.java)
    private val pokemonUseCase = PokemonUseCase(mockedRepository)

    @Before
    fun before() {
        `when`(mockedRepository.getPokemonList()).thenReturn(pokemonList)
    }

    @Test
    fun `test if getPokemon return PokemonView List correctly`() {
        val pokemonList = pokemonUseCase.getPokemonList()

        Assert.assertEquals(pokemonViewList, pokemonList)
    }
}