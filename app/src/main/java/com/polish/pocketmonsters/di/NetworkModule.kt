package com.polish.pocketmonsters.di

import com.polish.pocketmonsters.apiendpoint.APIPokemon
import com.polish.pocketmonsters.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * this is an object generator for network request operation
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    /**
     * httpLogging interceptor is a debugging tool
     * this creates an instance of it
     */
    @Provides
    @Singleton
    fun provideLogger():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }
    /**
     * this converter helps for serialization of objects(java object)
     * from object to btye stream and back to object
     */
    fun providesConverterFactory(): Converter.Factory{
        return GsonConverterFactory.create()
    }
    /**
     * OkHttp is an http client used for making Http request
     */
    @Provides
    @Singleton
    fun provideClient(logger:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
    /**
     * an instance of the retrofit object
     * this is an httpt client used to make the network call
     */
    fun provideService(client: OkHttpClient, converterFactory:GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

    }
    /**
     * create an instance of the APIPokemon
     */
    @Provides
    @Singleton
    fun provideAPIPokemonService(pokemonRetrofit: Retrofit):APIPokemon{
        return pokemonRetrofit.create(APIPokemon::class.java)
    }

}