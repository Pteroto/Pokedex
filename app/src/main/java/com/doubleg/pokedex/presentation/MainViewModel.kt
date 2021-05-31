package com.doubleg.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doubleg.pokedex.domain.PokemonUseCase
import com.doubleg.pokedex.domain.model.PokemonView
import com.doubleg.pokedex.repository.RepositoryImpl
import com.doubleg.pokedex.repository.retrofit.ApiBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//https://developer.android.com/topic/libraries/architecture/viewmodel
class MainViewModel : ViewModel() {
    private val useCase: PokemonUseCase

    init {
        val apiBuilder = ApiBuilder()
        val retrofit = apiBuilder.createRetrofit()
        val retrofitService = apiBuilder.createPokemonApi(retrofit)
        val repository = RepositoryImpl(retrofitService)
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