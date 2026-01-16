package com.example.proyectofinal.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos")
public class PedidoEntity {

    @PrimaryKey(autoGenerate = true)
    private int idPedido;

    private int idUsuario;
    private double total;
    private String fecha;

    public PedidoEntity(int idUsuario, double total, String fecha) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecha = fecha;
    }

    @Ignore
    public PedidoEntity(int idUsuario, double total) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecha = "";
    }

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
