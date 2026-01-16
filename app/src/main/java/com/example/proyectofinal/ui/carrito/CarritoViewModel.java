package com.example.proyectofinal.ui.carrito;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.data.repository.CarritoRepository;

import java.util.List;

public class CarritoViewModel extends AndroidViewModel {

    private final CarritoRepository repo;

    public CarritoViewModel(@NonNull Application application) {
        super(application);
        repo = new CarritoRepository(application);
    }

    public LiveData<List<CarritoProducto>> listarCarritoConProducto(int idUsuario) {
        return repo.listarCarritoConProducto(idUsuario);
    }

    public void agregarOIncrementar(int idUsuario, int idProducto) {
        repo.agregarOIncrementar(idUsuario, idProducto);
    }

    public void cambiarCantidad(int idCarritoItem, int idUsuario, int idProducto, int nuevaCantidad) {
        repo.cambiarCantidad(idCarritoItem, idUsuario, idProducto, nuevaCantidad);
    }

    public void eliminar(int idCarritoItem) {
        repo.eliminar(idCarritoItem);
    }

    public void vaciar(int idUsuario) {
        repo.vaciar(idUsuario);
    }
}
