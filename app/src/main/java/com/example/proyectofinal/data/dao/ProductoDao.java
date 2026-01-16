package com.example.proyectofinal.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectofinal.data.entities.ProductoEntity;

import java.util.List;

@Dao
public interface ProductoDao {

    @Insert
    void insertar(ProductoEntity producto);

    @Update
    void actualizar(ProductoEntity producto);

    @Delete
    void eliminar(ProductoEntity producto);

    @Query("SELECT * FROM productos WHERE categoria = :categoria ORDER BY idProducto DESC")
    LiveData<List<ProductoEntity>> listarPorCategoria(String categoria);

    @Query("SELECT * FROM productos WHERE categoria = :categoria AND (nombre LIKE '%' || :texto || '%' OR descripcion LIKE '%' || :texto || '%') ORDER BY idProducto DESC")
    LiveData<List<ProductoEntity>> buscarEnCategoria(String categoria, String texto);

    // ✅ Para seed
    @Query("SELECT COUNT(*) FROM productos")
    int contarProductosSync();
}
