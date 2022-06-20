package com.sergeymikhovich.android.chef.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergeymikhovich.android.chef.model.Measurement
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Query("SELECT * FROM measurement")
    suspend fun getMeasurements(): List<Measurement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeasurements(measurements: List<Measurement>)
}