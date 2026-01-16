package com.example.proyectofinal.ui.pedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.databinding.FragmentMisPedidosBinding;

public class MisPedidosFragment extends Fragment {

    private FragmentMisPedidosBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMisPedidosBinding.inflate(inflater, container, false);

        binding.tvTitulo.setText("Mis pedidos");
        binding.tvInfo.setText("Aquí se mostrarán pedidos simulados (historial).");

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
