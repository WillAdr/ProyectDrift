package com.example.proyectofinal.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "pedido_detalle",
        foreignKeys = {
                @ForeignKey(entity = PedidoEntity.class,
                        parentColumns = "idPedido",
                        childColumns = "idPedido",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = ProductoEntity.class,
                        parentColumns = "idProducto",
                        childColumns = "idProducto",
                        onDelete = ForeignKey.NO_ACTION)
        },
        indices = {@Index("idPedido"), @Index("idProducto")}
)
public class PedidoDetalleEntity {

    @PrimaryKey(autoGenerate = true)
    private int idDetalle;

    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precioUnitarioMXN;

    public PedidoDetalleEntity(int idPedido, int idProducto, int cantidad, double precioUnitarioMXN) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitarioMXN = precioUnitarioMXN;
    }

    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitarioMXN() { return precioUnitarioMXN; }
    public void setPrecioUnitarioMXN(double precioUnitarioMXN) { this.precioUnitarioMXN = precioUnitarioMXN; }
}
