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

    public void checkout(int idUsuario, List<CarritoProducto> carrito, double total, String fecha, Runnable onSuccess) {
        ProyectoFinalDatabase.DB_EXECUTOR.execute(() -> {
            String resumen = buildResumen(carrito);
            pedidoDao.insertar(new PedidoEntity(idUsuario, total, fecha, resumen));
            carritoDao.vaciarCarrito(idUsuario);
            if (onSuccess != null) onSuccess.run();
        });
    }

    private String buildResumen(List<CarritoProducto> carrito) {
        if (carrito == null || carrito.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < carrito.size(); i++) {
            CarritoProducto it = carrito.get(i);
            if (it == null) continue;
            String nom = it.nombre == null ? "" : it.nombre.trim();
            if (nom.isEmpty()) continue;
            sb.append(it.cantidad).append("x ").append(nom);
            if (i < carrito.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
