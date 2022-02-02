package com.example.taskapp.data

import android.content.Context
import androidx.room.Room

class RoomClient {

    fun provideDatabase(context: Context?): AppDataBase {
        return Room.databaseBuilder(context!!, AppDataBase::class.java, "dataBase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}