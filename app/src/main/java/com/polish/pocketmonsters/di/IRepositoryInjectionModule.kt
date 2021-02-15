package com.polish.pocketmonsters.di

import com.polish.pocketmonsters.repository.IRepository
import com.polish.pocketmonsters.repository.IRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * this module is responsibile for providing data to the viewmodels
 * the IrepositoryImpl holds all the data from remote source
 * so to make it available, we add it to a module and provide it
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class IRepositoryInjectionModule {
    @Binds
    abstract fun bindToInterface(repositoryImpl:IRepositoryImpl):IRepository
}