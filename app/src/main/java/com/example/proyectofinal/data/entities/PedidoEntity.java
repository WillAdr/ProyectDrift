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
    private String resumenProductos;

    public PedidoEntity(int idUsuario, double total, String fecha, String resumenProductos) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecha = fecha;
        this.resumenProductos = resumenProductos;
    }

    @Ignore
    public PedidoEntity(int idUsuario, double total) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecha = "";
        this.resumenProductos = "";
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getResumenProductos() { return resumenProductos; }
    public void setResumenProductos(String resumenProductos) { this.resumenProductos = resumenProductos; }
}
