package com.example.proyectofinal.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

@Database(
        entities = {UsuarioEntity.class, CarritoItemEntity.class, ProductoEntity.class, PedidoEntity.class},
        version = 2,
        exportSchema = false
)
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
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ProyectoFinalDatabase.class,
                                    "proyecto_final_db"
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(SEED_CALLBACK)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback SEED_CALLBACK = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            DB_EXECUTOR.execute(() -> {
                if (INSTANCE == null) return;

                UsuarioDao uDao = INSTANCE.usuarioDao();
                ProductoDao pDao = INSTANCE.productoDao();

                if (uDao.contarUsuariosSync() == 0) {
                    uDao.insertar(new UsuarioEntity("Usuario Demo", "demo@proyectofinal.com", "1234"));
                    uDao.insertar(new UsuarioEntity("Admin", "admin@proyectofinal.com", "admin123"));
                }

                if (pDao.contarProductosSync() == 0) {

                    pDao.insertar(new ProductoEntity("PIEZAS", "Volante deportivo", "Volante estilo drift con agarre premium.", 1899.00, "vol"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Kit suspensión", "Suspensión ajustable para control en curvas.", 6499.00, "kit"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Freno hidráulico", "Ideal para drifting y maniobras rápidas.", 2799.00, "fre"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Llantas semislick", "Agarre alto para pista y calle.", 8200.00, "lla"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Clutch reforzado", "Resiste torque alto para builds.", 4599.00, "clu"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Intercooler", "Mejora el rendimiento del turbo.", 3299.00, "int"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Downpipe", "Flujo optimizado para escape.", 2199.00, "dow"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Bujías iridium", "Encendido estable y eficiente.", 799.00, "buj"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Filtro alto flujo", "Más aire, mejor respuesta.", 499.00, "fil"));
                    pDao.insertar(new ProductoEntity("PIEZAS", "Radiador aluminio", "Mejor enfriamiento para track.", 2899.00, "rad"));

                    pDao.insertar(new ProductoEntity("ROPA", "Gorra Track Day", "Gorra ajustable estilo racing.", 299.00, "gor"));
                    pDao.insertar(new ProductoEntity("ROPA", "Playera JDM Night", "Arte inspirado en cultura JDM.", 349.00, "pla"));
                    pDao.insertar(new ProductoEntity("ROPA", "Hoodie Drift Culture", "Hoodie urbano automotriz.", 799.00, "hoo"));
                    pDao.insertar(new ProductoEntity("ROPA", "Chamarra Pit Crew", "Chamarra ligera estilo paddock.", 999.00, "cha"));
                    pDao.insertar(new ProductoEntity("ROPA", "Pants Street Racing", "Comodidad con estilo racing.", 699.00, "pan"));
                    pDao.insertar(new ProductoEntity("ROPA", "Calcetas Turbo", "Pack deportivo para diario.", 199.00, "cal"));
                    pDao.insertar(new ProductoEntity("ROPA", "Lentes Night Run", "Lentes oscuros para look nocturno.", 399.00, "len"));
                    pDao.insertar(new ProductoEntity("ROPA", "Guantes Grip", "Mejor agarre para volante.", 249.00, "gua"));
                    pDao.insertar(new ProductoEntity("ROPA", "Cinturón Racing", "Cinturón con hebilla robusta.", 279.00, "cin"));
                    pDao.insertar(new ProductoEntity("ROPA", "Mochila Drift", "Mochila compacta estilo street.", 599.00, "moc"));
                }
            });
        }
    };
}
