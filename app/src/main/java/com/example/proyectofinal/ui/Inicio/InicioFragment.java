package com.example.proyectofinal.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.databinding.FragmentHomeBinding;

public class InicioFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Animación de entrada
        View root = binding.getRoot();
        root.setAlpha(0f);
        root.setTranslationY(10f);
        root.animate().alpha(1f).translationY(0f).setDuration(220).start();

        // Minijuego: agrega lógica aquí para que funcione el juego (botón de inicio del juego)
        binding.btnStartGame.setOnClickListener(v -> {
            // Aquí abres el minijuego (lógica del juego)
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
