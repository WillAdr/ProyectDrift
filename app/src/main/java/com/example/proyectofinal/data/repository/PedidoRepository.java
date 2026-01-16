package com.example.proyectofinal.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectofinal.data.dao.CarritoDao;
import com.example.proyectofinal.data.dao.PedidoDao;
import com.example.proyectofinal.data.db.ProyectoFinalDatabase;
import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.data.entities.PedidoEntity;

import java.util.List;

public class PedidoRepository {

    private final PedidoDao pedidoDao;
    private final CarritoDao carritoDao;

    public PedidoRepository(Application app) {
        ProyectoFinalDatabase db = ProyectoFinalDatabase.getInstancia(app);
        pedidoDao = db.pedidoDao();
        carritoDao = db.carritoDao();
    }

    public LiveData<List<PedidoEntity>> listarPedidosPorUsuario(int idUsuario) {
        return pedidoDao.listarPedidosPorUsuario(idUsuario);
    }

    // ✅ checkout: inserta pedido + vacía carrito
    public void checkout(int idUsuario, List<CarritoProducto> carrito, double total, String fecha, Runnable onSuccess) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> {
            // Inserta pedido
            pedidoDao.insertar(new PedidoEntity(idUsuario, total, fecha));

            // Vacía carrito
            carritoDao.vaciarCarrito(idUsuario);

            if (onSuccess != null) onSuccess.run();
        });
    }
}
