package com.example.letrix.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LetrixSQLiteHelper extends SQLiteOpenHelper {

    // Nombre de la BBDD
    private static final String NOMBRE_BD = "letrix.db";

    // Cambios en tablas
    private static final int VERSION_BD = 1;

    //Ponemos nombre a las tablas y columnas creadas proximamente.
    public static final String TABLA_USUARIO = "usuario";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_USUARIO = "usuario";
    public static final String COLUMNA_CONTRASENA = "contrasena";

    //Creamos la tabla con sus primaryKey y demás
    private static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_USUARIO + " TEXT NOT NULL UNIQUE, " + COLUMNA_CONTRASENA + " TEXT NOT NULL)";

    public LetrixSQLiteHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    //Se ejecuta al iniciar el programa solo una vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }


    //Se ejecuta cuando se actualiza Version_Bd borrando las tablas y creandolas de nuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        onCreate(db);
    }
}
