package com.excitedbroltd.roomdatabaseandroid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val country: String
)