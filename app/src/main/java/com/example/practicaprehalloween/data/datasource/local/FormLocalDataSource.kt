package com.example.practicaprehalloween.data.datasource.local

import com.example.practicaprehalloween.data.datasource.local.db.FormDao
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import kotlinx.coroutines.flow.Flow

class FormLocalDataSource(private val formDao: FormDao) {

    // La función ahora acepta los tres parámetros
    suspend fun saveForm(name: String, powerRanger: String, cartoon: String) {
        // Creamos la nueva Entity con los datos
        val newEntry = FormEntity(
            name = name,
            powerRanger = powerRanger,
            cartoon = cartoon
        )
        formDao.insert(newEntry)
    }

    fun getAllFormData(): Flow<List<FormEntity>> {
        return formDao.getAll()
    }
}