package com.doubleg.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doubleg.pokedex.domain.PokemonUseCase
import com.doubleg.pokedex.domain.model.PokemonView
import com.doubleg.pokedex.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val useCase: PokemonUseCase

    init {
        val repository = Repository()
        useCase = PokemonUseCase(repository)
    }

//    private val _pokemonList: LiveData<List<PokemonView>> = liveData {
//        emit(useCase.getPokemonList())
//    }

    private val _pokemonList: MutableLiveData<List<PokemonView>> = MutableLiveData()
    val pokemonList: LiveData<List<PokemonView>>
        get() = _pokemonList

    fun getPokemonList() {
        //https://developer.android.com/kotlin/coroutines
        //Criando Thread em background
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonList.postValue(useCase.getPokemonList())
        }
    }
}