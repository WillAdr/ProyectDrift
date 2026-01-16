package com.example.proyectofinal.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.dao.UsuarioDao;
import com.example.proyectofinal.data.db.ProyectoFinalDatabase;
import com.example.proyectofinal.data.entities.UsuarioEntity;

public class UsuarioRepository {

    private final UsuarioDao usuarioDao;

    public UsuarioRepository(Application app) {
        ProyectoFinalDatabase db = ProyectoFinalDatabase.getInstancia(app);
        this.usuarioDao = db.usuarioDao();
    }

    public LiveData<UsuarioEntity> login(String correo, String password) {
        return usuarioDao.login(correo, password);
    }

    public LiveData<UsuarioEntity> buscarPorCorreo(String correo) {
        return usuarioDao.buscarPorCorreo(correo);
    }

    public void registrar(UsuarioEntity u) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> usuarioDao.insertar(u));
    }
}
