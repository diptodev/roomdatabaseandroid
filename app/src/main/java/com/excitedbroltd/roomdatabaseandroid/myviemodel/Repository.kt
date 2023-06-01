package com.excitedbroltd.roomdatabaseandroid.myviemodel

import androidx.lifecycle.LiveData
import com.excitedbroltd.roomdatabaseandroid.Person
import com.excitedbroltd.roomdatabaseandroid.PersonDao

class Repository(private val personDao: PersonDao) {
    fun getAllData(): LiveData<List<Person>> = personDao.readAllPerson()
    suspend fun addUser(person: Person) {
        personDao.addUser(person)
    }

}