package com.example.letrix.modelo.partida;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;

public class PartidaDAO {

    private final LetrixSQLiteHelper helper;

    public PartidaDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Crear una partida nueva al empezar el juego
    public void insertar(Partida partida) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LetrixSQLiteHelper.COLUMNA_ID_USUARIO, partida.getIdUsuario());
        values.put(LetrixSQLiteHelper.COLUMNA_ID_PALABRA, partida.getIdPalabra());
        values.put(LetrixSQLiteHelper.COLUMNA_NUMERO_INTENTOS, partida.getNumeroIntentos());
        values.put(LetrixSQLiteHelper.COLUMNA_RESULTADO, partida.isResultado() ? 1 : 0);
        long idGenerado = db.insert(LetrixSQLiteHelper.TABLA_PARTIDA, null, values);
        partida.setId((int) idGenerado);
    }

    // Actualizar la partida cuando termina
    public void actualizar(Partida partida) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LetrixSQLiteHelper.COLUMNA_NUMERO_INTENTOS, partida.getNumeroIntentos());
        values.put(LetrixSQLiteHelper.COLUMNA_RESULTADO, partida.isResultado() ? 1 : 0);
        db.update(LetrixSQLiteHelper.TABLA_PARTIDA, values,
                LetrixSQLiteHelper.COLUMNA_ID + "=?",
                new String[]{String.valueOf(partida.getId())});
    }

    // Obtener la partida por id
    public Partida obtenerPorId(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(LetrixSQLiteHelper.TABLA_PARTIDA, null,
                    LetrixSQLiteHelper.COLUMNA_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                return new Partida(
                        cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_USUARIO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_PALABRA)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_NUMERO_INTENTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_RESULTADO)) == 1
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }
}
