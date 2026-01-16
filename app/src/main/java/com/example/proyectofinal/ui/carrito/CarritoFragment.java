package com.example.proyectofinal.ui.carrito;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.data.repository.SesionManager;
import com.example.proyectofinal.databinding.FragmentCarritoBinding;
import com.example.proyectofinal.ui.adapter.CarritoAdapter;
import com.example.proyectofinal.ui.perfil.PedidosViewModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CarritoFragment extends Fragment implements CarritoAdapter.Listener {

    private FragmentCarritoBinding binding;
    private CarritoViewModel vmCarrito;
    private PedidosViewModel vmPedidos;
    private CarritoAdapter adapter;
    private SesionManager sesion;

    private int idUsuario = -1;
    private List<CarritoProducto> carritoActual = null;
    private double totalActual = 0.0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCarritoBinding.inflate(inflater, container, false);

        sesion = new SesionManager(requireContext());

        if (!sesion.haySesion() || sesion.esAdmin()) {
            binding.tvTitulo.setText("Carrito");
            binding.tvTotal.setText("Inicia sesión como usuario para usar el carrito.");
            binding.btnVaciar.setEnabled(false);
            binding.btnComprar.setEnabled(false);
            return binding.getRoot();
        }

        idUsuario = sesion.getIdUsuario();

        vmCarrito = new ViewModelProvider(this).get(CarritoViewModel.class);
        vmPedidos = new ViewModelProvider(this).get(PedidosViewModel.class);

        adapter = new CarritoAdapter(requireContext(), this);
        binding.rvCarrito.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCarrito.setAdapter(adapter);

        vmCarrito.listarCarritoConProducto(idUsuario).observe(getViewLifecycleOwner(), lista -> {
            carritoActual = lista;
            adapter.setLista(lista);
            actualizarTotal(lista);

            boolean hayItems = (lista != null && !lista.isEmpty());
            binding.btnComprar.setEnabled(hayItems);
            binding.btnVaciar.setEnabled(hayItems);
        });

        binding.btnVaciar.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Vaciar carrito")
                .setMessage("¿Seguro que deseas vaciar el carrito?")
                .setPositiveButton("Sí", (d, w) -> vmCarrito.vaciar(idUsuario))
                .setNegativeButton("No", null)
                .show());

        binding.btnComprar.setOnClickListener(v -> {
            if (carritoActual == null || carritoActual.isEmpty()) {
                Toast.makeText(requireContext(), "Tu carrito está vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            mostrarDialogoCheckout();
        });

        return binding.getRoot();
    }

    private void actualizarTotal(List<CarritoProducto> lista) {
        double total = 0.0;
        if (lista != null) {
            for (CarritoProducto item : lista) total += (item.precioMXN * item.cantidad);
        }
        totalActual = total;

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        binding.tvTotal.setText("Total: " + formato.format(total) + " MXN");
    }

    private void mostrarDialogoCheckout() {
        View v = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_checkout, null);

        EditText etNombreRecibe = v.findViewById(R.id.etNombreRecibe);
        EditText etDireccion = v.findViewById(R.id.etDireccion);
        EditText etTelefono = v.findViewById(R.id.etTelefono);
        EditText etNotas = v.findViewById(R.id.etNotas);
        RadioGroup rgPago = v.findViewById(R.id.rgPago);

        new AlertDialog.Builder(requireContext())
                .setTitle("Finalizar compra")
                .setView(v)
                .setPositiveButton("Enviar pedido", (d, w) -> {

                    String nombre = etNombreRecibe.getText().toString().trim();
                    String direccion = etDireccion.getText().toString().trim();
                    String tel = etTelefono.getText().toString().trim();

                    if (nombre.isEmpty() || direccion.isEmpty() || tel.isEmpty()) {
                        Toast.makeText(requireContext(), "Completa nombre, dirección y teléfono", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int pagoId = rgPago.getCheckedRadioButtonId();
                    if (pagoId == -1) {
                        Toast.makeText(requireContext(), "Selecciona un método de pago", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    vmPedidos.checkout(idUsuario, carritoActual, totalActual, () -> {
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "Pedido enviado con éxito", Toast.LENGTH_LONG).show()
                        );
                    });

                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override public void onMas(CarritoProducto item) { vmCarrito.cambiarCantidad(item.idCarritoItem, item.idUsuario, item.idProducto, item.cantidad + 1); }
    @Override public void onMenos(CarritoProducto item) { vmCarrito.cambiarCantidad(item.idCarritoItem, item.idUsuario, item.idProducto, item.cantidad - 1); }
    @Override public void onQuitar(CarritoProducto item) { vmCarrito.eliminar(item.idCarritoItem); }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
