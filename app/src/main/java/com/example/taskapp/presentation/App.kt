package com.example.taskapp.presentation

import android.app.Application
import androidx.room.Room
import com.example.taskapp.data.AppDataBase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appDataBase = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "dataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object{
        lateinit var appDataBase: AppDataBase
    }
}