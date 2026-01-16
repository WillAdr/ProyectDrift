package com.example.proyectofinal.data.entities;

public class CarritoProducto {

    public int idCarritoItem;
    public int idUsuario;
    public int idProducto;
    public String nombre;
    public String descripcion;
    public double precioMXN;
    public String imagen;
    public int cantidad;

    // Constructor
    public CarritoProducto(int idCarritoItem, int idUsuario, int idProducto,
                           String nombre, String descripcion, double precioMXN,
                           String imagen, int cantidad) {
        this.idCarritoItem = idCarritoItem;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioMXN = precioMXN;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdCarritoItem() { return idCarritoItem; }
    public void setIdCarritoItem(int idCarritoItem) { this.idCarritoItem = idCarritoItem; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioMXN() { return precioMXN; }
    public void setPrecioMXN(double precioMXN) { this.precioMXN = precioMXN; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
