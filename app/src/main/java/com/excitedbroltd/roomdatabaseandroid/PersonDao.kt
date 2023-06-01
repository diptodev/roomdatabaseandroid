package com.excitedbroltd.roomdatabaseandroid

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(person: Person)

    @Query("SELECT * FROM userDetails ORDER BY id ASC")
    fun readAllPerson(): LiveData<List<Person>>
}