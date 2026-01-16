package com.example.proyectofinal.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.proyectofinal.data.repository.SesionManager;
import com.example.proyectofinal.data.repository.ThemeManager;
import com.example.proyectofinal.databinding.FragmentPerfilBinding;
import com.example.proyectofinal.ui.adapter.PedidoAdapter;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private SesionManager sesion;
    private PedidosViewModel vmPedidos;
    private PedidoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        // micro animación
        View root = binding.getRoot();
        root.setAlpha(0f);
        root.setTranslationY(12f);
        root.animate().alpha(1f).translationY(0f).setDuration(220).start();

        sesion = new SesionManager(requireContext());
        vmPedidos = new ViewModelProvider(this).get(PedidosViewModel.class);

        adapter = new PedidoAdapter(requireContext());
        binding.rvPedidos.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvPedidos.setAdapter(adapter);

        if (!sesion.haySesion()) {
            binding.tvPerfilInfo.setText("Sesión: (no iniciada)");
        } else {
            binding.tvPerfilInfo.setText("Sesión: " + sesion.getCorreo() + (sesion.esAdmin() ? " (ADMIN)" : " (USUARIO)"));

            if (!sesion.esAdmin()) {
                vmPedidos.listarPedidos(sesion.getIdUsuario()).observe(getViewLifecycleOwner(), lista -> {
                    adapter.setLista(lista);
                    binding.rvPedidos.setAlpha(0f);
                    binding.rvPedidos.animate().alpha(1f).setDuration(180).start();
                });
            }
        }

        // temas (si ya los tienes)
        ThemeManager tm = new ThemeManager(requireContext());
        binding.btnTemaOscuro.setOnClickListener(v -> { tm.setTema(ThemeManager.TEMA_OSCURO); requireActivity().recreate(); });
        binding.btnTemaRojo.setOnClickListener(v -> { tm.setTema(ThemeManager.TEMA_ROJO); requireActivity().recreate(); });
        binding.btnTemaAzul.setOnClickListener(v -> { tm.setTema(ThemeManager.TEMA_AZUL); requireActivity().recreate(); });
        binding.btnTemaMorado.setOnClickListener(v -> { tm.setTema(ThemeManager.TEMA_MORADO); requireActivity().recreate(); });
        binding.btnTemaVerde.setOnClickListener(v -> { tm.setTema(ThemeManager.TEMA_VERDE); requireActivity().recreate(); });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
