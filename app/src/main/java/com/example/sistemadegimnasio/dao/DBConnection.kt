package com.example.sistemadegimnasio.dao

import android.content.Context
import androidx.room.Room

object DBConnection {
    @Volatile
    private var instance: AppDatabase? = null

    fun getConnection(): AppDatabase {
        return instance
            ?: throw IllegalStateException("Database not built. Call build(context) first.")
    }

    fun buildConnection(context: Context): AppDatabase {
        synchronized(this) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "gymDB"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

//    fun foo(context: Context): AppDatabase? {
//        if (instance == null) {
//            Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java, "gymDB"
//            ).allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//        return instance
//    }

//    private fun foo2(context: Context) = Room.databaseBuilder(
//        context.applicationContext,
//        AppDatabase::class.java, "gymDB"
//    ).allowMainThreadQueries()
//        .fallbackToDestructiveMigration()
//        .build()
}