package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RackDao {
    @Query("SELECT * FROM racks")
    fun getAllRacks(): Flow<List<Rack>>

    @Insert()
    suspend fun addRack(rack: Rack)
}