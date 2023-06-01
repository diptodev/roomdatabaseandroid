package com.excitedbroltd.roomdatabaseandroid

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person)

    @Query("SELECT * FROM userDetails ORDER BY id ASC")
    fun readAllPerson(): LiveData<List<Person>>

    @Delete
    suspend fun deletePerson(person: Person)
}