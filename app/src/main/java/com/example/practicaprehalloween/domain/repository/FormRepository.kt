package com.example.practicaprehalloween.domain.repository

import kotlinx.coroutines.flow.Flow


interface FormRepository {
    //Aca definimos la funcion que el UseCase puede llamar
    suspend fun saveForm(data: String)
    fun getFormData(): Flow<String>
}