package com.example.proyectofinal.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "usuarios",
        indices = {@Index(value = {"correo"}, unique = true)}
)
public class UsuarioEntity {

    @PrimaryKey(autoGenerate = true)
    private int idUsuario;

    @NonNull
    private String nombre;

    @NonNull
    private String correo;

    @NonNull
    private String password;

    public UsuarioEntity(@NonNull String nombre, @NonNull String correo, @NonNull String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @NonNull
    public String getNombre() { return nombre; }
    public void setNombre(@NonNull String nombre) { this.nombre = nombre; }

    @NonNull
    public String getCorreo() { return correo; }
    public void setCorreo(@NonNull String correo) { this.correo = correo; }

    @NonNull
    public String getPassword() { return password; }
    public void setPassword(@NonNull String password) { this.password = password; }
}
