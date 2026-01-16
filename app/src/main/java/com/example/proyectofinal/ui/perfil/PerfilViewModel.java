package com.example.proyectofinal.ui.perfil;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.PedidoEntity;
import com.example.proyectofinal.data.repository.PedidoRepository;

import java.util.List;

public class PerfilViewModel extends AndroidViewModel {

    private final PedidoRepository pedidoRepository;

    public PerfilViewModel(Application application) {
        super(application);
        pedidoRepository = new PedidoRepository(application);
    }

    // Obtener la lista de pedidos sin pasar un idUsuario explícito
    public LiveData<List<PedidoEntity>> obtenerPedidos() {
        return pedidoRepository.listarPedidos();
    }
}
