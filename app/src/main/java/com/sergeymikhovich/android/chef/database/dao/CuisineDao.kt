package com.sergeymikhovich.android.chef.database.dao

import androidx.room.*
import com.sergeymikhovich.android.chef.model.Cuisine
import kotlinx.coroutines.flow.Flow

@Dao
interface CuisineDao {

    @Query("SELECT * FROM cuisine")
    suspend fun getCuisines(): List<Cuisine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCuisines(cuisines: List<Cuisine>)
}