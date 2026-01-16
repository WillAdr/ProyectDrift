package com.example.proyectofinal.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.entities.CarritoProducto;
import com.example.proyectofinal.databinding.ItemCarritoBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoVH> {

    public interface Listener {
        void onMas(CarritoProducto item);
        void onMenos(CarritoProducto item);
        void onQuitar(CarritoProducto item);
    }

    private final Context context;
    private final Listener listener;
    private final List<CarritoProducto> lista = new ArrayList<>();

    public CarritoAdapter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setLista(List<CarritoProducto> nueva) {
        lista.clear();
        if (nueva != null) lista.addAll(nueva);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarritoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarritoBinding binding = ItemCarritoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CarritoVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoVH holder, int position) {
        CarritoProducto item = lista.get(position);

        holder.binding.tvNombre.setText(item.nombre);

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        holder.binding.tvPrecio.setText(formato.format(item.precioMXN) + " MXN");

        holder.binding.tvCantidad.setText(String.valueOf(item.cantidad));

        int resId = context.getResources().getIdentifier(item.imagen, "drawable", context.getPackageName());
        if (resId == 0) resId = R.drawable.ic_placeholder_producto;
        holder.binding.imgProducto.setImageResource(resId);

        holder.binding.btnMas.setOnClickListener(v -> listener.onMas(item));
        holder.binding.btnMenos.setOnClickListener(v -> listener.onMenos(item));
        holder.binding.btnQuitar.setOnClickListener(v -> listener.onQuitar(item));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class CarritoVH extends RecyclerView.ViewHolder {
        final ItemCarritoBinding binding;

        public CarritoVH(@NonNull ItemCarritoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
