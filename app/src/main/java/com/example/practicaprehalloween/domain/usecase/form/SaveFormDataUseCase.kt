package com.example.practicaprehalloween.domain.usecase.form

import com.example.practicaprehalloween.domain.repository.FormRepository

class SaveFormDataUseCase(
    //Aca revibimos el repositorio (la interfaz, mas no la implementacion)
    private val repository: FormRepository
) {
    //Hacemos una funcion de tipo suspend para que podamos se pueda usar en una corutina
    //Usamos "operator fun invoke" para poder llamar a la clase como si fuera una funcion
    suspend operator fun invoke(data: String) {
        if (data.isBlank()) {
            //Esta seria la logica que estariamos siguiendo
            throw Exception("Los datos no pueden estar vacios.")
        }
        //La logica del caso de uso. Entonces "llamamos" al repositorio
        repository.saveForm(data)
    }
}
