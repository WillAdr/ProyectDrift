package com.example.proyectofinal.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectofinal.data.entities.CarritoItemEntity;
import com.example.proyectofinal.data.entities.CarritoProducto;

import java.util.List;

@Dao
public interface CarritoDao {

    @Insert
    void insertar(CarritoItemEntity item);

    @Update
    void actualizar(CarritoItemEntity item);

    @Query("DELETE FROM carrito_items WHERE idCarritoItem = :idCarritoItem")
    void eliminarPorId(int idCarritoItem);

    @Query("DELETE FROM carrito_items WHERE idUsuario = :idUsuario")
    void vaciarCarrito(int idUsuario);

    @Query("SELECT ci.idCarritoItem, ci.idUsuario, ci.idProducto, ci.cantidad, " +
            "p.nombre, p.descripcion, p.precioMXN, p.imagen " +
            "FROM carrito_items ci " +
            "INNER JOIN productos p ON ci.idProducto = p.idProducto " +
            "WHERE ci.idUsuario = :idUsuario")
    LiveData<List<CarritoProducto>> listarCarritoConProducto(int idUsuario);

    @Query("SELECT * FROM carrito_items WHERE idUsuario = :idUsuario AND idProducto = :idProducto LIMIT 1")
    CarritoItemEntity buscarItemSync(int idUsuario, int idProducto);
}
