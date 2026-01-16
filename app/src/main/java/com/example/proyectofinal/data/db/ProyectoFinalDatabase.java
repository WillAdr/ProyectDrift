package com.example.proyectofinal.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectofinal.data.dao.CarritoDao;
import com.example.proyectofinal.data.dao.PedidoDao;
import com.example.proyectofinal.data.dao.ProductoDao;
import com.example.proyectofinal.data.dao.UsuarioDao;
import com.example.proyectofinal.data.entities.CarritoItemEntity;
import com.example.proyectofinal.data.entities.PedidoEntity;
import com.example.proyectofinal.data.entities.ProductoEntity;
import com.example.proyectofinal.data.entities.UsuarioEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UsuarioEntity.class, CarritoItemEntity.class, ProductoEntity.class, PedidoEntity.class}, version = 1, exportSchema = false)
public abstract class ProyectoFinalDatabase extends RoomDatabase {

    public static final ExecutorService DB_EXECUTOR = Executors.newFixedThreadPool(4);

    private static volatile ProyectoFinalDatabase INSTANCE;


    public abstract UsuarioDao usuarioDao();
    public abstract CarritoDao carritoDao();
    public abstract ProductoDao productoDao();
    public abstract PedidoDao pedidoDao();


    public static ProyectoFinalDatabase getInstancia(Context context) {
        if (INSTANCE == null) {
            synchronized (ProyectoFinalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProyectoFinalDatabase.class, "proyecto_final_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
