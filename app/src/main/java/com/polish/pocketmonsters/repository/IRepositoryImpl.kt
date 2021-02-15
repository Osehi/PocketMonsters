package com.polish.pocketmonsters.repository

import com.polish.pocketmonsters.networkdatamodel.Result
import javax.inject.Inject

class IRepositoryImpl @Inject constructor(): IRepository {
    override suspend fun allPokemon(limit: String): Result {
        return Result()

    }
}