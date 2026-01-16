package com.example.proyectofinal.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.proyectofinal.R;

public class ThemeManager {

    public static final String TEMA_OSCURO = "oscuro";
    public static final String TEMA_ROJO   = "rojo";
    public static final String TEMA_AZUL   = "azul";
    public static final String TEMA_MORADO = "morado";
    public static final String TEMA_VERDE  = "verde";

    private static final String PREF = "tema_pref";
    private static final String KEY_TEMA = "tema";

    private final SharedPreferences sp;

    public ThemeManager(Context context) {
        sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void setTema(String tema) {
        sp.edit().putString(KEY_TEMA, tema).apply();
    }

    public String getTema() {
        return sp.getString(KEY_TEMA, TEMA_OSCURO);
    }

    // Devuelve el style que se aplica a la app
    public int getThemeResId() {
        switch (getTema()) {
            case TEMA_ROJO:   return R.style.Theme_ProyectoFinal_Rojo;
            case TEMA_AZUL:   return R.style.Theme_ProyectoFinal_Azul;
            case TEMA_MORADO: return R.style.Theme_ProyectoFinal_Morado;
            case TEMA_VERDE:  return R.style.Theme_ProyectoFinal_Verde;
            default:          return R.style.Theme_ProyectoFinal_Oscuro;
        }
    }
}
