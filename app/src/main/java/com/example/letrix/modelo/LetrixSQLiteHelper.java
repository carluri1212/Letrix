package com.example.letrix.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class LetrixSQLiteHelper extends SQLiteOpenHelper {

    // Nombre del fichero de base de datos que se creara en el movil.
    private static final String NOMBRE_BD = "letrix.db";

    // Version de la BBDD. Si en el futuro cambia la estructura de las
    // tablas, se sube este numero y se dispara onUpgrade().
    private static final int VERSION_BD = 1;

    // Nombres de la tabla y de sus columnas.
    // Se ponen como constantes para no escribir los textos a mano
    // (y que se equivoque uno) por todo el codigo del DAO.
    public static final String TABLA_USUARIO = "usuario";
    public static final String COL_ID = "id";
    public static final String COL_USUARIO = "usuario";
    public static final String COL_CONTRASENA = "contrasena";

    // Sentencia SQL que crea la tabla 'usuario'.
    //  - id: clave primaria que se autoincrementa sola.
    //  - usuario: texto, obligatorio y UNICO (no puede haber dos
    //    usuarios con el mismo nombre).
    //  - contrasena: texto, obligatorio.
    private static final String CREAR_TABLA_USUARIO =
            "CREATE TABLE " + TABLA_USUARIO + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_USUARIO + " TEXT NOT NULL UNIQUE, " +
                    COL_CONTRASENA + " TEXT NOT NULL)";

    /**
     * @param context el contexto de Android. Se lo pasara la Activity
     *                (la pantalla) al crear el DAO.
     */
    public LetrixSQLiteHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    /**
     * Se ejecuta UNA SOLA VEZ: la primera vez que la app necesita la
     * base de datos y esta todavia no existe. Aqui se crean las tablas.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    /**
     * Se ejecuta cuando sube el numero de VERSION_BD. Para un proyecto
     * de clase, lo mas sencillo es borrar la tabla y volver a crearla.
     * (En una app real se migrarian los datos sin perderlos.)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        onCreate(db);
    }
}
