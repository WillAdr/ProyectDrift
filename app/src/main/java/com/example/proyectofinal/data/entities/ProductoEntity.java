package com.example.proyectofinal.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos")
public class ProductoEntity {
    @PrimaryKey(autoGenerate = true)
    private int idProducto;
    private String categoria;
    private String nombre;
    private String descripcion;
    private double precioMXN;
    private String imagen;

    // Constructor con los parámetros correctos
    public ProductoEntity(String categoria, String nombre, String descripcion, double precioMXN, String imagen) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioMXN = precioMXN;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioMXN() { return precioMXN; }
    public void setPrecioMXN(double precioMXN) { this.precioMXN = precioMXN; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
