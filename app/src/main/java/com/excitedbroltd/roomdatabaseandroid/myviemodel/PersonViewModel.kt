package com.excitedbroltd.roomdatabaseandroid.myviemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.excitedbroltd.roomdatabaseandroid.MyRoomDatabase
import com.excitedbroltd.roomdatabaseandroid.Person
import com.excitedbroltd.roomdatabaseandroid.PersonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private var personDao: PersonDao = MyRoomDatabase.getInstance(application).personDao()
    private var repository: Repository = Repository(personDao)
    val name: String = ""
    fun getAllPerson() = repository.getAllData()

    fun addPerson(id: Int, name: String, age: Int, country: String) {
        val person = Person(id, name, age, country)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(person)
        }
    }
}