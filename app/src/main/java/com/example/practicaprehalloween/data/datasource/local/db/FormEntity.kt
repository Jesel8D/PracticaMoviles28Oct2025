package com.example.practicaprehalloween.data.datasource.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity le dice a Room que esta clase es una tabla
@Entity(tableName = "form_data_table")
data class FormEntity(
    // @PrimaryKey le dice a Room cuál es la ID única
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val data: String, // La columna para guardar tus datos
    val timestamp: Long = System.currentTimeMillis() // Para saber cuándo se guardó
)