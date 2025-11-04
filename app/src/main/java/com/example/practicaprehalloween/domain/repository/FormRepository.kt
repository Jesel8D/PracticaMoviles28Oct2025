package com.example.practicaprehalloween.domain.repository

interface FormRepository {
    //Aca definimos la funcion que el UseCase puede llamar
    suspend fun saveForm(data: String)
}