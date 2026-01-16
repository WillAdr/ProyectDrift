package com.example.proyectofinal.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.dao.CarritoDao;
import com.example.proyectofinal.data.db.ProyectoFinalDatabase;
import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.data.entities.CarritoItemEntity;

import java.util.List;

public class CarritoRepository {

    private final CarritoDao carritoDao;

    public CarritoRepository(Application app) {
        ProyectoFinalDatabase db = ProyectoFinalDatabase.getInstancia(app);
        this.carritoDao = db.carritoDao();
    }

    public LiveData<List<CarritoProducto>> listarCarritoConProducto(int idUsuario) {
        return carritoDao.listarCarritoConProducto(idUsuario);
    }

    public void agregarOIncrementar(int idUsuario, int idProducto) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> {
            CarritoItemEntity existente = carritoDao.buscarItemSync(idUsuario, idProducto);
            if (existente == null) {
                carritoDao.insertar(new CarritoItemEntity(idUsuario, idProducto, 1));
            } else {
                existente.setCantidad(existente.getCantidad() + 1);
                carritoDao.actualizar(existente);
            }
        });
    }

    public void cambiarCantidad(int idCarritoItem, int idUsuario, int idProducto, int nuevaCantidad) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> {
            if (nuevaCantidad <= 0) {
                carritoDao.eliminarPorId(idCarritoItem);
            } else {
                CarritoItemEntity item = new CarritoItemEntity(idUsuario, idProducto, nuevaCantidad);
                item.setIdCarritoItem(idCarritoItem);
                carritoDao.actualizar(item);
            }
        });
    }

    public void eliminar(int idCarritoItem) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> carritoDao.eliminarPorId(idCarritoItem));
    }

    public void vaciar(int idUsuario) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> carritoDao.vaciarCarrito(idUsuario));
    }
}
