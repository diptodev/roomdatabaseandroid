package com.excitedbroltd.roomdatabaseandroid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null
        fun getInstance(context: Context): MyRoomDatabase {
            val instance = INSTANCE
            if (instance != null) return instance
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "userDetails"
                ).build()
                INSTANCE = newInstance
                return newInstance
            }

        }
    }


}