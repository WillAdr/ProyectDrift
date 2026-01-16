package com.example.proyectofinal.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.data.entities.ProductoEntity;
import com.example.proyectofinal.databinding.ItemProductoBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoVH> {

    public interface Listener {
        void onEditar(ProductoEntity producto);
        void onEliminar(ProductoEntity producto);
        void onAgregarCarrito(ProductoEntity producto);
    }

    private final Context context;
    private final Listener listener;
    private final List<ProductoEntity> lista = new ArrayList<>();
    private boolean modoAdmin = false;

    public ProductoAdapter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setModoAdmin(boolean modoAdmin) {
        this.modoAdmin = modoAdmin;
        notifyDataSetChanged();
    }

    public void setLista(List<ProductoEntity> nuevaLista) {
        lista.clear();
        if (nuevaLista != null) lista.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductoVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        ProductoEntity p = lista.get(position);

        holder.binding.tvNombre.setText(p.getNombre());
        holder.binding.tvDescripcion.setText(p.getDescripcion());

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        holder.binding.tvPrecio.setText(formato.format(p.getPrecioMXN()) + " MXN");

        int resId = context.getResources().getIdentifier(
                p.getImagen(),
                "drawable",
                context.getPackageName()
        );
        if (resId == 0) resId = R.drawable.ic_placeholder_producto;
        holder.binding.imgProducto.setImageResource(resId);

        // ✅ Admin: editar/eliminar
        holder.binding.btnEditar.setVisibility(modoAdmin ? View.VISIBLE : View.GONE);
        holder.binding.btnEliminar.setVisibility(modoAdmin ? View.VISIBLE : View.GONE);

        // ✅ Usuario: añadir carrito (el botón debe existir en el XML)
        holder.binding.btnAgregarCarrito.setVisibility(modoAdmin ? View.GONE : View.VISIBLE);

        holder.binding.btnEditar.setOnClickListener(v -> listener.onEditar(p));
        holder.binding.btnEliminar.setOnClickListener(v -> listener.onEliminar(p));
        holder.binding.btnAgregarCarrito.setOnClickListener(v -> listener.onAgregarCarrito(p));

        // Animación simple
        holder.itemView.setAlpha(0f);
        holder.itemView.setTranslationY(18f);
        holder.itemView.animate().alpha(1f).translationY(0f).setDuration(200).start();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ProductoVH extends RecyclerView.ViewHolder {
        final ItemProductoBinding binding;

        public ProductoVH(@NonNull ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
