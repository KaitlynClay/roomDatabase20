package com.example.roomdatabase20.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase20.model.User

// Contains the database holder and server as the main access point for
// the underlying connection to your app's persisted, relational data
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao():UserDao
    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDataBase(context: Context):UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}