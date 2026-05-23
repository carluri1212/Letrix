package com.example.letrix.modelo.categoria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    //Nos conectamos a la BBDD con este atributo:
    private final LetrixSQLiteHelper helper;
    // Conseguimos el Context de Android para poder abrir la BBDD
    public CategoriaDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Metodo que muestra todas las categorías por pantalla
    public List<Categoria> obtenerTodas() {
        List<Categoria> lista = new ArrayList<>();
        // Abrimos la BD solo en modo lectura porque no vamos a modificar nada, SIMPLEMENTE MOSTRAR
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            //db.query es lo mismo que hacer un select * from Categoría en SQL
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA, null, null, null, null, null, null);
            // Si hay al menos una fila, entramos
            if (cursor.moveToFirst()) {
                do {
                    lista.add(new Categoria(
                            // Creamos un objeto Categoria con cada fila que encontramos
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

    // Metodo que recupera la categoría elegida por el usuario.
    public Categoria obtenerPorId(String id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            // SELECT * FROM categoria WHERE id = ?
            // ? se sustituye por el id que obtenemos/recibimos.
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA, null,
                    LetrixSQLiteHelper.COLUMNA_ID + "=?", new String[]{id},
                    null, null, null);
            // Si encontramos la categoría la devolvemos
            if (cursor.moveToFirst()) {
                return new Categoria(
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_NOMBRE_CATEGORIA))
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        // Si no existe esa categoría devolvemos null
        return null;
    }
    // Busca el id de una categoria por su nombre
    public int buscarIdPorNombre(String nombre) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        int id = -1;
        try {
            //SELECT id FROM categoria WHERE nombreCategoria = ?
            cursor = db.query(LetrixSQLiteHelper.TABLA_CATEGORIA,
                    new String[]{LetrixSQLiteHelper.COLUMNA_ID},
                    LetrixSQLiteHelper.COLUMNA_NOMBRE_CATEGORIA + "=?",
                    new String[]{nombre}, null, null, null);
            if (cursor.moveToFirst()) {
                //Cogemos el valor de la primera columna nada más (id)
                id = cursor.getInt(0);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return id;
    }
}
