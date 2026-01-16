package com.example.proyectofinal.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectofinal.data.entities.PedidoEntity;

import java.util.List;

@Dao
public interface PedidoDao {

    @Insert
    long insertar(PedidoEntity pedido);

    // ✅ Pedidos por usuario (esto es lo que debes usar en Perfil)
    @Query("SELECT * FROM pedidos WHERE idUsuario = :idUsuario ORDER BY idPedido DESC")
    LiveData<List<PedidoEntity>> listarPedidosPorUsuario(int idUsuario);

    @Query("DELETE FROM pedidos WHERE idPedido = :idPedido")
    void eliminarPedido(int idPedido);
}
