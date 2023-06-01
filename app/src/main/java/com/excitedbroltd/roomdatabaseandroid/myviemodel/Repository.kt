package com.excitedbroltd.roomdatabaseandroid.myviemodel

import androidx.lifecycle.LiveData
import com.excitedbroltd.roomdatabaseandroid.Person
import com.excitedbroltd.roomdatabaseandroid.PersonDao

class Repository(private val personDao: PersonDao) {
    fun getAllData(): LiveData<List<Person>> = personDao.readAllPerson()
    suspend fun addPerson(person: Person) {
        personDao.addPerson(person)
    }

    suspend fun deletePerson(person: Person) {
        personDao.deletePerson(person)
    }

    suspend fun updatePerson(person: Person) {
        personDao.updatePerson(person)
    }
}