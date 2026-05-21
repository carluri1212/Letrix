package com.example.letrix.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LetrixSQLiteHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "letrix.db";
    private static final int VERSION_BD = 1;

    // Tabla Usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_USUARIO = "usuario";
    public static final String COLUMNA_CONTRASENA = "contrasena";
    public static final String COLUMNA_PUNTUACION = "puntuacion";

    // Tabla Categoria
    public static final String TABLA_CATEGORIA = "categoria";
    public static final String COLUMNA_NOMBRE_CATEGORIA = "nombreCategoria";

    // Tabla Palabra
    public static final String TABLA_PALABRA = "palabra";
    public static final String COLUMNA_TEXTO = "texto";
    public static final String COLUMNA_ID_CATEGORIA = "idCategoria";

    // Tabla Partida
    public static final String TABLA_PARTIDA = "partida";
    public static final String COLUMNA_ID_USUARIO = "idUsuario";
    public static final String COLUMNA_ID_PALABRA = "idPalabra";
    public static final String COLUMNA_NUMERO_INTENTOS = "numeroIntentos";
    public static final String COLUMNA_RESULTADO = "resultado";

    // Tabla Letra
    public static final String TABLA_LETRA = "letra";
    public static final String COLUMNA_ID_PARTIDA = "idPartida";
    public static final String COLUMNA_CARACTER = "caracter";
    public static final String COLUMNA_POSICION = "posicion";
    public static final String COLUMNA_ESTADO = "estado";

    // Sentencias CREATE
    private static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_USUARIO + " TEXT NOT NULL UNIQUE, " +
            COLUMNA_CONTRASENA + " TEXT NOT NULL, " +
            COLUMNA_PUNTUACION + " INTEGER DEFAULT 0)";

    private static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE " + TABLA_CATEGORIA + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_NOMBRE_CATEGORIA + " TEXT NOT NULL)";

    private static final String CREAR_TABLA_PALABRA = "CREATE TABLE " + TABLA_PALABRA + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_TEXTO + " TEXT NOT NULL, " +
            COLUMNA_ID_CATEGORIA + " INTEGER, " +
            "FOREIGN KEY (" + COLUMNA_ID_CATEGORIA + ") REFERENCES " + TABLA_CATEGORIA + "(" + COLUMNA_ID + "))";

    private static final String CREAR_TABLA_PARTIDA = "CREATE TABLE " + TABLA_PARTIDA + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_ID_USUARIO + " INTEGER, " +
            COLUMNA_ID_PALABRA + " INTEGER, " +
            COLUMNA_NUMERO_INTENTOS + " INTEGER DEFAULT 0, " +
            COLUMNA_RESULTADO + " INTEGER DEFAULT 0, " +
            "FOREIGN KEY (" + COLUMNA_ID_USUARIO + ") REFERENCES " + TABLA_USUARIO + "(" + COLUMNA_ID + "), " +
            "FOREIGN KEY (" + COLUMNA_ID_PALABRA + ") REFERENCES " + TABLA_PALABRA + "(" + COLUMNA_ID + "))";

    private static final String CREAR_TABLA_LETRA = "CREATE TABLE " + TABLA_LETRA + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_ID_PARTIDA + " INTEGER, " +
            COLUMNA_CARACTER + " TEXT NOT NULL, " +
            COLUMNA_POSICION + " INTEGER, " +
            COLUMNA_ESTADO + " TEXT, " +
            "FOREIGN KEY (" + COLUMNA_ID_PARTIDA + ") REFERENCES " + TABLA_PARTIDA + "(" + COLUMNA_ID + "))";

    public LetrixSQLiteHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL(CREAR_TABLA_CATEGORIA);
        db.execSQL(CREAR_TABLA_PALABRA);
        db.execSQL(CREAR_TABLA_PARTIDA);
        db.execSQL(CREAR_TABLA_LETRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_LETRA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PARTIDA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PALABRA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        onCreate(db);
    }
}
