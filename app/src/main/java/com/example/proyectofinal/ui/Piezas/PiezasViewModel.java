package com.example.proyectofinal.ui.Piezas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.ProductoEntity;
import com.example.proyectofinal.data.repository.ProductoRepository;

import java.util.List;

public class PiezasViewModel extends AndroidViewModel {

    private final ProductoRepository repo;

    public PiezasViewModel(@NonNull Application application) {
        super(application);
        repo = new ProductoRepository(application);
    }

    public LiveData<List<ProductoEntity>> getPiezas() {
        return repo.listarPorCategoria("PIEZAS");
    }

    public LiveData<List<ProductoEntity>> buscar(String texto) {
        return repo.buscarEnCategoria("PIEZAS", texto);
    }

    public void insertar(ProductoEntity p) { repo.insertar(p); }
    public void actualizar(ProductoEntity p) { repo.actualizar(p); }
    public void eliminar(ProductoEntity p) { repo.eliminar(p); }
}
