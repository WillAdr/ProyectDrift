package com.example.proyectofinal.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class SesionManager {

    private static final String PREF = "sesion_pref";
    private static final String KEY_ID_USUARIO = "idUsuario";
    private static final String KEY_CORREO = "correo";

    private final SharedPreferences sp;

    public SesionManager(Context context) {
        sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void guardarSesion(int idUsuario, String correo) {
        sp.edit()
                .putInt(KEY_ID_USUARIO, idUsuario)
                .putString(KEY_CORREO, correo)
                .apply();
    }

    public void cerrarSesion() {
        sp.edit().clear().apply();
    }

    public boolean haySesion() {
        return sp.contains(KEY_ID_USUARIO);
    }

    public int getIdUsuario() {
        return sp.getInt(KEY_ID_USUARIO, -1);
    }

    public String getCorreo() {
        return sp.getString(KEY_CORREO, "");
    }

    public boolean esAdmin() {
        return "admin@proyectofinal.com".equalsIgnoreCase(getCorreo());
    }
}
