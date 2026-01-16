package com.example.proyectofinal.ui.perfil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.PedidoEntity;
import com.example.proyectofinal.data.repository.PedidoRepository;

import java.util.List;

public class PerfilViewModel extends AndroidViewModel {

    private final PedidoRepository pedidoRepository;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        pedidoRepository = new PedidoRepository(application);
    }

    public LiveData<List<PedidoEntity>> obtenerPedidos(int idUsuario) {
        return pedidoRepository.listarPedidosPorUsuario(idUsuario);
    }
}
