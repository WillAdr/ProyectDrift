package com.example.proyectofinal.ui.Piezas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.proyectofinal.data.entities.ProductoEntity;
import com.example.proyectofinal.data.repository.SesionManager;
import com.example.proyectofinal.databinding.DialogProductoBinding;
import com.example.proyectofinal.databinding.FragmentGalleryBinding;
import com.example.proyectofinal.ui.adapter.ProductoAdapter;
import com.example.proyectofinal.ui.carrito.CarritoViewModel;

public class PiezasFragment extends Fragment implements ProductoAdapter.Listener {

    private FragmentGalleryBinding binding;
    private PiezasViewModel vm;
    private ProductoAdapter adapter;
    private SesionManager sesion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        sesion = new SesionManager(requireContext());
        vm = new ViewModelProvider(this).get(PiezasViewModel.class);

        adapter = new ProductoAdapter(requireContext(), this);
        adapter.setModoAdmin(sesion.esAdmin());

        binding.rvProductos.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProductos.setAdapter(adapter);

        binding.btnAgregar.setVisibility(sesion.esAdmin() ? View.VISIBLE : View.GONE);

        vm.getPiezas().observe(getViewLifecycleOwner(), adapter::setLista);

        binding.etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String texto = s.toString().trim();
                if (texto.isEmpty()) {
                    vm.getPiezas().observe(getViewLifecycleOwner(), adapter::setLista);
                } else {
                    vm.buscar(texto).observe(getViewLifecycleOwner(), adapter::setLista);
                }
            }
        });

        binding.btnAgregar.setOnClickListener(v -> {
            if (!sesion.esAdmin()) return;
            mostrarDialogoProducto(null);
        });

        return binding.getRoot();
    }

    private void mostrarDialogoProducto(@Nullable ProductoEntity productoEditar) {
        DialogProductoBinding d = DialogProductoBinding.inflate(getLayoutInflater());
        boolean esEdicion = (productoEditar != null);

        if (esEdicion) {
            d.etNombre.setText(productoEditar.getNombre());
            d.etDescripcion.setText(productoEditar.getDescripcion());
            d.etPrecio.setText(String.valueOf(productoEditar.getPrecioMXN()));
            d.etImagen.setText(productoEditar.getImagen());
        }

        new AlertDialog.Builder(requireContext())
                .setTitle(esEdicion ? "Editar pieza" : "Agregar pieza")
                .setView(d.getRoot())
                .setPositiveButton(esEdicion ? "Guardar" : "Agregar", (dialog, which) -> {

                    String nombre = d.etNombre.getText().toString().trim();
                    String desc = d.etDescripcion.getText().toString().trim();
                    String precioTxt = d.etPrecio.getText().toString().trim();
                    String imagen = d.etImagen.getText().toString().trim();

                    if (nombre.isEmpty() || desc.isEmpty() || precioTxt.isEmpty()) {
                        Toast.makeText(requireContext(), "Completa nombre, descripción y precio", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double precio;
                    try {
                        precio = Double.parseDouble(precioTxt);
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), "Precio inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (imagen.isEmpty()) imagen = "ic_placeholder_producto";

                    if (esEdicion) {
                        productoEditar.setNombre(nombre);
                        productoEditar.setDescripcion(desc);
                        productoEditar.setPrecioMXN(precio);
                        productoEditar.setImagen(imagen);
                        vm.actualizar(productoEditar);
                    } else {
                        vm.insertar(new ProductoEntity("PIEZAS", nombre, desc, precio, imagen));
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public void onEditar(ProductoEntity producto) {
        if (!sesion.esAdmin()) return;
        mostrarDialogoProducto(producto);
    }

    @Override
    public void onEliminar(ProductoEntity producto) {
        if (!sesion.esAdmin()) return;
        new AlertDialog.Builder(requireContext())
                .setTitle("Eliminar")
                .setMessage("¿Eliminar \"" + producto.getNombre() + "\"?")
                .setPositiveButton("Sí", (d, w) -> vm.eliminar(producto))
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onAgregarCarrito(ProductoEntity producto) {
        if (sesion.esAdmin()) return;

        if (!sesion.haySesion()) {
            Toast.makeText(requireContext(), "Inicia sesión para usar el carrito", Toast.LENGTH_SHORT).show();
            return;
        }

        CarritoViewModel carritoVM = new ViewModelProvider(this).get(CarritoViewModel.class);
        carritoVM.agregarOIncrementar(sesion.getIdUsuario(), producto.getIdProducto());
        Toast.makeText(requireContext(), "Añadido al carrito", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
