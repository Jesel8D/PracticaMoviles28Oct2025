package com.example.practicaprehalloween.presentation.screens.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicaprehalloween.domain.usecase.form.SaveFormDataUseCase


//Aca estaria nuestra "fabrica" por asi decirlo, de manera manual,
//Recibe las dependencias que el ViewModel necesita
class FormViewModelFactory(
    private val saveFormDataUseCase: SaveFormDataUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            //Si Andorid pide un FormViewModel, entonces lo construimos
            //Y le "inyectamos" su dependencia
            @Suppress("UNCHEKED_CAST")
            return FormViewModel(saveFormDataUseCase) as T
        } throw IllegalArgumentException(
            "Clase de ViewModel mala" +
                    ""
        )
    }

}
