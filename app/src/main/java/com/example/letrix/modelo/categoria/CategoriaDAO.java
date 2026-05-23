package com.example.letrix.modelo.categoria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final LetrixSQLiteHelper helper;

    public CategoriaDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Cargar las categorías para mostrarlas en pantalla
    public List<Categoria> obtenerTodas() {
        List<Categoria> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    lista.add(new Categoria(
                            cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_NOMBRE_CATEGORIA))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return lista;
    }

    // Recuperar la categoría elegida por el usuario
    public Categoria obtenerPorId(String id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA, null,
                    LetrixSQLiteHelper.COLUMNA_ID + "=?", new String[]{id},
                    null, null, null);
            if (cursor.moveToFirst()) {
                return new Categoria(
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_NOMBRE_CATEGORIA))
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }
    // Busca el id de una categoria por su nombre
    public int buscarIdPorNombre(String nombre) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        int id = -1;
        try {
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA,
                    new String[]{LetrixSQLiteHelper.COLUMNA_ID},
                    LetrixSQLiteHelper.COLUMNA_NOMBRE_CATEGORIA + "=?",
                    new String[]{nombre}, null, null, null);
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return id;
    }
}
