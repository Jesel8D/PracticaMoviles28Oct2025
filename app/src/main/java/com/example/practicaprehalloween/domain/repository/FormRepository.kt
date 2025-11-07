package com.example.practicaprehalloween.domain.repository

import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import kotlinx.coroutines.flow.Flow

interface FormRepository {
    // Actualizamos la firma para que acepte los tres strings
    suspend fun saveForm(name: String, powerRanger: String, cartoon: String)

    fun getFormData(): Flow<List<FormEntity>>
}