package com.example.proyectofinal.ui.Registro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectofinal.data.entities.UsuarioEntity;
import com.example.proyectofinal.data.repository.UsuarioRepository;

public class RegistroViewModel extends AndroidViewModel {

    private final UsuarioRepository usuarioRepo;

    private final MediatorLiveData<Boolean> isSuccess = new MediatorLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        usuarioRepo = new UsuarioRepository(application);
        isSuccess.setValue(false);
    }

    public LiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public void registrar(UsuarioEntity nuevoUsuario) {

        if (nuevoUsuario == null) {
            mensaje.setValue("Datos inválidos.");
            isSuccess.setValue(false);
            return;
        }

        String correo = nuevoUsuario.getCorreo();

        LiveData<UsuarioEntity> fuente = usuarioRepo.buscarPorCorreo(correo);

        // Observa UNA vez si existe correo
        isSuccess.addSource(fuente, existente -> {
            // Quitamos la fuente para no observar para siempre
            isSuccess.removeSource(fuente);

            if (existente != null) {
                mensaje.setValue("Ese correo ya está registrado.");
                isSuccess.setValue(false);
                return;
            }

            // Si NO existe → registrar (en tu repo ya es async)
            try {
                usuarioRepo.registrar(nuevoUsuario);
                mensaje.setValue("Registro exitoso. Ahora inicia sesión.");
                isSuccess.setValue(true);
            } catch (Exception e) {
                mensaje.setValue("Error al registrar. Intenta de nuevo.");
                isSuccess.setValue(false);
            }
        });
    }
}
