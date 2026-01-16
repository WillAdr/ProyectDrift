package com.example.proyectofinal.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carrito_items")
public class CarritoItemEntity {

    @PrimaryKey(autoGenerate = true)
    private int idCarritoItem;
    private int idUsuario;
    private int idProducto;
    private int cantidad;

    // Constructor, Getters, Setters
    public CarritoItemEntity(int idUsuario, int idProducto, int cantidad) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdCarritoItem() { return idCarritoItem; }
    public void setIdCarritoItem(int idCarritoItem) { this.idCarritoItem = idCarritoItem; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
