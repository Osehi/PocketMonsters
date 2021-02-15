package com.polish.pocketmonsters.repository

import android.util.Log
import com.polish.pocketmonsters.apiendpoint.APIPokemon
import com.polish.pocketmonsters.networkdatamodel.Result
import com.polish.pocketmonsters.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class IRepositoryImpl @Inject constructor(val pokemonApi:APIPokemon): IRepository {
    override suspend fun allPokemon(limit: String): DataState<Result> {
        return withContext(Dispatchers.IO){
            try {
                DataState.Success(pokemonApi.allPokemons(limit))
            } catch (throwable:Throwable){
                when(throwable){
                    is HttpException -> {
                        DataState.Error(false, throwable.code(), throwable.response() as retrofit2.Response<Any>)
                    }
                    else -> {
                        Log.d("NETWORKERROR", "${throwable}")
                        DataState.Error(true, null, null)
                    }
                }
            }
        }

    }
}