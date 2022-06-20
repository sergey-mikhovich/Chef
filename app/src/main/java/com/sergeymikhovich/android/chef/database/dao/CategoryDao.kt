package com.sergeymikhovich.android.chef.database.dao

import androidx.room.*
import com.sergeymikhovich.android.chef.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(categories: List<Category>)
}