package com.sergeymikhovich.android.chef.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.sergeymikhovich.android.chef.database.ChefDatabase
import com.sergeymikhovich.android.chef.database.converters.CookingStepEntryConverter
import com.sergeymikhovich.android.chef.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "Chef"

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, gson: Gson): ChefDatabase {
        CookingStepEntryConverter.initializeGson(gson)
        return Room.databaseBuilder(
            context,
            ChefDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: ChefDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideCuisineDao(database: ChefDatabase): CuisineDao = database.cuisineDao()

    @Provides
    @Singleton
    fun provideRecipeDao(database: ChefDatabase): RecipeDao = database.recipeDao()

    @Provides
    @Singleton
    fun provideCompositionItemDao(database: ChefDatabase): CompositionDao = database.compositionItemDao()

    @Provides
    @Singleton
    fun provideIngredientDao(database: ChefDatabase): IngredientDao = database.ingredientDao()

    @Provides
    @Singleton
    fun provideMeasurementDao(database: ChefDatabase): MeasurementDao = database.measurementDao()
}