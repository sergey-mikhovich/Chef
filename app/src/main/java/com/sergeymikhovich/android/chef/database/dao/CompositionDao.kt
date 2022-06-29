package com.sergeymikhovich.android.chef.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sergeymikhovich.android.chef.model.Composition
import com.sergeymikhovich.android.chef.model.relations.CompositionDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CompositionDao {

    @Query("SELECT * FROM composition")
    suspend fun getCompositions(): List<Composition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComposition(composition: Composition)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompositions(compositions: List<Composition>)

    @Transaction
    @Query("SELECT * FROM composition WHERE recipeId = :recipeId")
    suspend fun getCompositionsByRecipeId(recipeId: String): List<Composition>

    @Transaction
    @Query("SELECT * FROM composition WHERE recipeId = :recipeId")
    fun getCompositionsDetailsByRecipeId(recipeId: String): LiveData<List<CompositionDetails>>

    @Transaction
    @Delete
    suspend fun deleteComposition(composition: Composition)
}