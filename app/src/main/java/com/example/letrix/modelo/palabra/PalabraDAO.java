package com.example.letrix.modelo.palabra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;

public class PalabraDAO {

    private final LetrixSQLiteHelper helper;

    public PalabraDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Obtenemos una palabra aleatoria según la categoría elegida
    public Palabra obtenerPalabraAleatoria(String idCategoria) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
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
}