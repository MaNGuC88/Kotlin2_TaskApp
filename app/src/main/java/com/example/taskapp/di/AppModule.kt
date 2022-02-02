package com.example.taskapp.di

import android.content.Context
import com.example.taskapp.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context?): AppDataBase {
        return RoomClient().provideDatabase(context)
    }

    @Provides
    fun provideWeatherDao(database: AppDataBase): ShopDao {
        return database.shopListDao()
    }

}