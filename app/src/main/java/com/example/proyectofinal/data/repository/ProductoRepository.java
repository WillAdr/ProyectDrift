package com.example.proyectofinal.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.dao.ProductoDao;
import com.example.proyectofinal.data.db.ProyectoFinalDatabase;
import com.example.proyectofinal.data.entities.ProductoEntity;

import java.util.List;

public class ProductoRepository {

    private final ProductoDao productoDao;

    public ProductoRepository(Application application) {
        ProyectoFinalDatabase db = ProyectoFinalDatabase.getInstancia(application);
        this.productoDao = db.productoDao();
    }

    public LiveData<List<ProductoEntity>> listarPorCategoria(String categoria) {
        return productoDao.listarPorCategoria(categoria);
    }

    public LiveData<List<ProductoEntity>> buscarEnCategoria(String categoria, String texto) {
        return productoDao.buscarEnCategoria(categoria, texto);
    }

    public void insertar(ProductoEntity producto) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> productoDao.insertar(producto));
    }

    public void actualizar(ProductoEntity producto) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> productoDao.actualizar(producto));
    }

    public void eliminar(ProductoEntity producto) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> productoDao.eliminar(producto));
    }
}
