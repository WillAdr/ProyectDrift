package com.example.proyectofinal.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectofinal.data.entities.UsuarioEntity;

@Dao
public interface UsuarioDao {

    @Insert
    void insertar(UsuarioEntity usuario);

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND password = :password LIMIT 1")
    LiveData<UsuarioEntity> login(String correo, String password);

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    LiveData<UsuarioEntity> buscarPorCorreo(String correo);
}
