package com.example.nasa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nasa.modal.Planetary

@Dao
interface PlanetaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(planetary: List<Planetary>)

    @Query("SELECT * FROM planetary")
    fun getAllPlanetary(): LiveData<List<Planetary>>

    @Query("DELETE FROM planetary")
    fun deleteAll()
}