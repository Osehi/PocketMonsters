package com.polish.pocketmonsters.repository

import com.polish.pocketmonsters.networkdatamodel.Result
import com.polish.pocketmonsters.utils.DataState
import retrofit2.http.Query

interface IRepository {

    suspend fun allPokemon(
        @Query("limit") limit:String
    ):DataState<Result>
}