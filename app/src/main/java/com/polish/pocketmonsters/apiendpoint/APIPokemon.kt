package com.polish.pocketmonsters.apiendpoint

import com.polish.pocketmonsters.networkdatamodel.PokemonCharacters
import com.polish.pocketmonsters.networkdatamodel.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface APIPokemon {
    @GET("pokemon")
    suspend fun allPokemons(
        @Query("limit") limit:String
    ):PokemonCharacters
}