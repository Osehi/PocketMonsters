package com.polish.pocketmonsters.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.polish.pocketmonsters.networkdatamodel.PokemonCharacters
import com.polish.pocketmonsters.networkdatamodel.Result
import com.polish.pocketmonsters.repository.IRepository
import com.polish.pocketmonsters.utils.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel@Inject constructor (private val repository:IRepository,  private val savesStateHandle: SavedStateHandle): ViewModel(){
    val TAG = "PokemonViewModel"
    /**
     * create viewmodel object
     */
    private var _getAllPokemonResponse = MutableLiveData<DataState<PokemonCharacters>>()
    val getAllPokemonResponse: LiveData<DataState<PokemonCharacters>>
    get() = _getAllPokemonResponse

    /**
     * create a coroutine scope to handle the data output from the input IO
     * and channel it to the main thread
     */
    private var job = Job()
    private var pokemonViewModelScope = CoroutineScope(job + Dispatchers.IO)



    /**
     * get all the pokemon characters
     */
    fun getAllPokemonCharacters(limit:String){
        pokemonViewModelScope.launch {
            val outputFromRemote = repository.allPokemon(limit)
            // to channel the data to the mainThread
            withContext(Dispatchers.Main){
                _getAllPokemonResponse.value = outputFromRemote
                Log.d(TAG, "the list of pokemon characters:${outputFromRemote}")
            }
        }
    }
}