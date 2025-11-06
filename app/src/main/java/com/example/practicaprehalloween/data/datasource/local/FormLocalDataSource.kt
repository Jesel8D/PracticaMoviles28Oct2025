package com.example.practicaprehalloween.data.datasource.local

// 1. Importamos las clases de la base de datos
import com.example.practicaprehalloween.data.datasource.local.db.FormDao
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import kotlinx.coroutines.flow.Flow

// 2. ¡Su dependencia ahora es el DAO!
class FormLocalDataSource(private val formDao: FormDao) {

    // 3. La función 'save' ahora crea un 'Entity' y la inserta
    //    (Sigue siendo una 'suspend fun' de coroutines)
    suspend fun saveForm(data: String) {
        val newEntry = FormEntity(data = data)
        formDao.insert(newEntry)
    }

    // 4. La función 'get' ahora devuelve una Lista de Entities desde el DAO
    fun getAllFormData(): Flow<List<FormEntity>> {
        return formDao.getAll()
    }
}