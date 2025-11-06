package com.example.practicaprehalloween.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaprehalloween.data.datasource.local.db.FormDao
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity

// 1. @Database define la base de datos, sus tablas (entities) y la versión
@Database(entities = [FormEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // 2. Conecta el DAO con la base de datos
    abstract fun formDao(): FormDao

    // 3. Este es el "Singleton pattern".
    //    Se asegura de que solo exista UNA instancia de tu base de datos
    //    en toda la app, lo cual es muy importante para el rendimiento.
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // El nombre del archivo de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}