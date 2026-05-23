package com.example.letrix.modelo.palabra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;
import android.content.ContentValues;

public class PalabraDAO {
    //Nos conectamos con la BBDD
    private final LetrixSQLiteHelper helper;
    // Conseguimos el Context de Android para poder abrir la BBDD
    public PalabraDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Metodo que devuelve una palabra aleatoria de la categoría que el usuario ha elegido
    public Palabra obtenerPalabraAleatoria(String idCategoria) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {

            // SELECT * FROM palabra WHERE idCategoria = ?
            // ORDER BY RANDOM() → coge una fila aleatoria
            // LIMIT 1 → solo una palabra
            cursor = db.query(LetrixSQLiteHelper.TABLA_PALABRA, null,
                    LetrixSQLiteHelper.COLUMNA_ID_CATEGORIA + "=?",
                    new String[]{idCategoria},
                    null, null, "RANDOM()", "1");

            if (cursor.moveToFirst()) {
                return new Palabra(
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_TEXTO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_CATEGORIA))
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    // Obtenemos una palabra por id (para recuperar una partida guardada)
    public Palabra obtenerPorId(String id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            // SELECT * FROM palabra WHERE id = ?
            cursor = db.query(LetrixSQLiteHelper.TABLA_PALABRA, null,
                    LetrixSQLiteHelper.COLUMNA_ID + "=?", new String[]{id},
                    null, null, null);
            if (cursor.moveToFirst()) {
                return new Palabra(
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_TEXTO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_CATEGORIA))
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    // Inserta una palabra nueva en la BBDD
    public void insertar(Palabra palabra) {
        // Abrimos en modo escritura porque vamos a añadir datos
        SQLiteDatabase db = helper.getWritableDatabase();

        /* ContentValues es lo mismo que  los valores del INSERT
         Solo ponemos texto e idCategoria porque el id lo genera la BD automáticamente
        con AUTOINCREMENT  */
        ContentValues valores = new ContentValues();
        valores.put(LetrixSQLiteHelper.COLUMNA_TEXTO, palabra.getTexto());
        valores.put(LetrixSQLiteHelper.COLUMNA_ID_CATEGORIA, palabra.getIdCategoria());

        // INSERT INTO palabra (texto, idCategoria) VALUES (?, ?)
        db.insert(LetrixSQLiteHelper.TABLA_PALABRA, null, valores);
    }

    /* Metodo que cuenta cuántas palabras hay en total en la BBDD
     que nos sirve para comprobar si la BBDD ya tiene palabras cargadas
     y evitaMOS insertar duplicados cada vez que arranca la app  */
    public int contarPalabras() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;

        // Empezamos en 0 por si la tabla está vacía
        int total = 0;

        try {
            /* Usamos rawQuery mejor porque db.query no deja poner el
            COUNT . Es lo mismo que : SELECT COUNT(*) FROM palabra
             */
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + LetrixSQLiteHelper.TABLA_PALABRA, null);

            if (cursor.moveToFirst()) {
                // COUNT(*) devuelve un único valor numérico en la primera columna
                total = cursor.getInt(0);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        // Devuelve el número total de palabras, o 0 si la tabla está vacía
        return total;
    }
}