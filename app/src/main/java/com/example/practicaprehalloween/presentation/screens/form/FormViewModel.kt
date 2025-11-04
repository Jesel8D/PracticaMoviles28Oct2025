package com.example.practicaprehalloween.presentation.screens.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaprehalloween.domain.usecase.form.SaveFormDataUseCase
import kotlinx.coroutines.launch

class FormViewModel(
    //En esta parte heredamos al model
    //El ViewModel recibe su logica que viene del (UseCase)
    private val saveFormDataUseCase: SaveFormDataUseCase
) : ViewModel()//Aca heredamos del ViewModel
{

    fun saveUserData(data: String) {
        //Usamos corrutinas
        viewModelScope.launch {
            try {
                //Aca esta la logica del ViewModel "Llamamos" al caso de uso
                saveFormDataUseCase(data)
            } catch (e: Exception) {
                //Aca podemos manejar un error
            }
        }

    }
}