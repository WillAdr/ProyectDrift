package com.example.proyectofinal.ui.Registro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.UsuarioEntity;
import com.example.proyectofinal.data.repository.UsuarioRepository;

public class LoginViewModel extends AndroidViewModel {

    private final UsuarioRepository usuarioRepo;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        usuarioRepo = new UsuarioRepository(application);
    }

    public LiveData<UsuarioEntity> login(String correo, String contrasena) {
        return usuarioRepo.login(correo, contrasena);
    }
}
