package com.example.proyectofinal.ui.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioViewModel extends ViewModel {

    private final MutableLiveData<String> texto = new MutableLiveData<>();

    public InicioViewModel() {
        // Puedes cambiar este texto cuando quieras
        texto.setValue("Bienvenido a ProyectoFinal");
    }

    public LiveData<String> getTexto() {
        return texto;
    }

    public void setTexto(String nuevo) {
        texto.setValue(nuevo);
    }
}
