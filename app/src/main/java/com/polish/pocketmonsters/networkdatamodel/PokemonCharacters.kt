package com.polish.pocketmonsters.networkdatamodel


import com.google.gson.annotations.SerializedName

data class PokemonCharacters(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result>?
)