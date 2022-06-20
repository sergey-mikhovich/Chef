package com.sergeymikhovich.android.chef.di

import com.sergeymikhovich.android.chef.repository.ChefRepository
import com.sergeymikhovich.android.chef.repository.ChefRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(impl: ChefRepositoryImpl): ChefRepository
}