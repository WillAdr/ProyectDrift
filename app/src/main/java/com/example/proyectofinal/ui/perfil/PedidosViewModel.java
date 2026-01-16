package com.example.proyectofinal.ui.perfil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.data.entities.PedidoEntity;
import com.example.proyectofinal.data.repository.PedidoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PedidosViewModel extends AndroidViewModel {

    private final PedidoRepository repo;

    public PedidosViewModel(@NonNull Application application) {
        super(application);
        repo = new PedidoRepository(application);
    }

    public LiveData<List<PedidoEntity>> listarPedidos(int idUsuario) {
        return repo.listarPedidosPorUsuario(idUsuario);
    }

    public void checkout(int idUsuario, List<CarritoProducto> carrito, double total, Runnable onSuccess) {
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        repo.checkout(idUsuario, carrito, total, fecha, onSuccess);
    }
}
