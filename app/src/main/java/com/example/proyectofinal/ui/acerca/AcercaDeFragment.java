package com.example.proyectofinal.ui.acerca;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.proyectofinal.R;

public class AcercaDeFragment extends Fragment {

    ObjectAnimator animCarro1;
    ObjectAnimator animCarro2;
    boolean carro1Rapido = true;  // Para alternar la velocidad de los carros

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        return inflater.inflate(R.layout.fragment_acerca_de, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicialización de los ImageViews de los carros
        ImageView carro1 = view.findViewById(R.id.carro1);
        ImageView carro2 = view.findViewById(R.id.carro2);

        // Definir la animación de los carros
        animCarro1 = ObjectAnimator.ofFloat(carro1, "x", 500f); // Mueve el carro 1 hasta 500f en el eje X
        animCarro2 = ObjectAnimator.ofFloat(carro2, "x", 500f); // Mueve el carro 2 hasta 500f en el eje X

        // Alternar las velocidades de los carros
        alternarVelocidades();

        // Configurar las animaciones para ambos carros
        animCarro1.setDuration(1500);  // Inicialmente lento
        animCarro2.setDuration(800);   // Inicialmente rápido
        animCarro1.start();
        animCarro2.start();

        // AnimatorSet para ejecutar ambas animaciones simultáneamente
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animCarro1, animCarro2);

        // Configurar el comportamiento cuando la animación termine
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Alternar las velocidades y reiniciar la animación
                alternarVelocidades();
                animation.start(); // Reinicia la animación después de cada ciclo
            }
        });

        set.start();  // Iniciar la animación
    }

    // Método para alternar las velocidades de los carros
    private void alternarVelocidades() {
        if (carro1Rapido) {
            animCarro1.setDuration(800);  // rápido
            animCarro2.setDuration(1500); // lento
        } else {
            animCarro1.setDuration(1500); // lento
            animCarro2.setDuration(800);  // rápido
        }
        carro1Rapido = !carro1Rapido; // Alterna el estado
    }
}
