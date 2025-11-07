package com.example.practicaprehalloween.data.datasource.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_data_table")
data class FormEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Estos son nuestros 3 campos
    val name: String,
    val powerRanger: String,
    val cartoon: String,

    val timestamp: Long = System.currentTimeMillis()
)