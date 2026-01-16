package com.example.proyectofinal.ui.Registro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.repository.SesionManager;
import com.example.proyectofinal.databinding.FragmentLoginBinding;
import com.example.proyectofinal.ui.util.GlassUtil;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        GlassUtil.applyGlass70Card(binding.cardLogin);

        binding.btnLogin.setOnClickListener(v -> {
            String correo = binding.etCorreo.getText().toString().trim();
            String contrasena = binding.etContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(getContext(), "Por favor, ingrese todos los datos.", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(correo, contrasena).observe(getViewLifecycleOwner(), usuario -> {
                if (usuario == null) {
                    Toast.makeText(getContext(), "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SesionManager sesionManager = new SesionManager(requireContext());
                sesionManager.guardarSesion(usuario.getIdUsuario(), usuario.getCorreo());

                Toast.makeText(getContext(), "Bienvenido, " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();

                NavController nav = Navigation.findNavController(binding.getRoot());
                nav.navigate(R.id.nav_home);
            });
        });

        binding.tvNoCuenta.setOnClickListener(v -> {
            NavController nav = Navigation.findNavController(binding.getRoot());
            nav.navigate(R.id.nav_registro);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
