package com.polish.pocketmonsters.utils

import org.junit.Assert.*
import org.junit.Test

class ExtentionFunctionsKtTest {
    @Test
    fun getUrlTest_whenUrlLengthIs29_returnOneDigitNumberTrue(){
        // given
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        // action
        val result = getUrl(url)
        // assert
        assertEquals("1",result)

    }

    @Test
    fun getUrlTest_whenUrlLengthIs30_returnTwoDigitNumberTrue(){
        // given
        val url = "https://pokeapi.co/api/v2/pokemon/40/"
        // action
        val result = getUrl(url)
        // assert
        assertEquals("40",result)

    }

}