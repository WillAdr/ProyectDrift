package com.example.proyectofinal.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.data.entities.PedidoEntity;
import com.example.proyectofinal.databinding.ItemPedidoBinding;
import com.example.proyectofinal.ui.util.GlassUtil;
import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoVH> {

    private final Context context;
    private final List<PedidoEntity> lista = new ArrayList<>();

    public PedidoAdapter(Context context) {
        this.context = context;
    }

    public void setLista(List<PedidoEntity> nuevaLista) {
        lista.clear();
        if (nuevaLista != null) lista.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PedidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPedidoBinding binding = ItemPedidoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new PedidoVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoVH holder, int position) {
        PedidoEntity pedido = lista.get(position);

        if (holder.binding.getRoot() instanceof MaterialCardView) {
            GlassUtil.applyGlass70Card((MaterialCardView) holder.binding.getRoot());
        }

        holder.binding.tvPedidoFecha.setText("Fecha: " + pedido.getFecha());

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        holder.binding.tvPedidoTotal.setText("Total: " + formato.format(pedido.getTotal()) + " MXN");

        holder.itemView.setAlpha(0f);
        holder.itemView.setTranslationY(18f);
        holder.itemView.animate().alpha(1f).translationY(0f).setDuration(200).start();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class PedidoVH extends RecyclerView.ViewHolder {
        ItemPedidoBinding binding;
        public PedidoVH(@NonNull ItemPedidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
