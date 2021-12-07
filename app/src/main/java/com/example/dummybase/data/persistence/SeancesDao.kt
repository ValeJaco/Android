package com.example.dummybase.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummybase.data.model.Seance

@Dao
interface SeancesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeances(seancesList: List<Seance>)

    @Query("SELECT * FROM Seance")
    fun getSeances(): LiveData<List<Seance>>

    @Query("SELECT * FROM Seance WHERE id = :id")
    fun getSeanceById(id: Int): LiveData<Seance>

}