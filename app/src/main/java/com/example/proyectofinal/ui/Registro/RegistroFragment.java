package com.example.proyectofinal.ui.Registro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.entities.UsuarioEntity;
import com.example.proyectofinal.databinding.FragmentRegistroBinding;
import com.example.proyectofinal.ui.util.GlassUtil;

public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;
    private RegistroViewModel registroViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        GlassUtil.applyGlass70Card(binding.cardRegistro);

        registroViewModel.getMensaje().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        registroViewModel.getIsSuccess().observe(getViewLifecycleOwner(), ok -> {
            if (ok != null && ok) {
                NavController nav = Navigation.findNavController(binding.getRoot());
                nav.navigate(R.id.nav_login);
            }
        });

        binding.btnRegistrar.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString().trim();
            String correo = binding.etCorreo.getText().toString().trim();
            String contrasena = binding.etContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(getContext(), "Por favor, ingrese todos los datos.", Toast.LENGTH_SHORT).show();
                return;
            }

            UsuarioEntity nuevo = new UsuarioEntity(nombre, correo, contrasena);
            registroViewModel.registrar(nuevo);
        });

        binding.tvYaCuenta.setOnClickListener(v -> {
            NavController nav = Navigation.findNavController(binding.getRoot());
            nav.navigate(R.id.nav_login);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
