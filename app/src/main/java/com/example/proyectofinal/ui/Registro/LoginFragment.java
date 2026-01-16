package com.example.proyectofinal.ui.Registro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.entities.UsuarioEntity;
import com.example.proyectofinal.data.repository.SesionManager;
import com.example.proyectofinal.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            String correo = binding.etCorreo.getText().toString();
            String contrasena = binding.etContrasena.getText().toString();

            if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(getContext(), "Por favor, ingrese todos los datos.", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(correo, contrasena).observe(getViewLifecycleOwner(), usuario -> {
                if (usuario == null) {
                    Toast.makeText(getContext(), "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Guardar sesión si login es exitoso
                SesionManager sesionManager = new SesionManager(getContext());
                sesionManager.guardarSesion(usuario.getIdUsuario(), usuario.getCorreo());
                Toast.makeText(getContext(), "Bienvenido, " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();
                // Redirigir a la página principal o perfil
            });
        });

        binding.tvNoCuenta.setOnClickListener(v -> {
            // Ir a RegistroFragment
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new RegistroFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return binding.getRoot();
    }
}
