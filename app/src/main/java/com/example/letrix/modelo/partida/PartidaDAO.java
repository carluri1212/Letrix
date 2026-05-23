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

    // Metodo que crea una partida nueva al empezar a jugar.
    public void insertar(Partida partida) {
        // Abrimos en modo escritura porque vamos a insertar datos
        SQLiteDatabase db = helper.getWritableDatabase();

        // ContentValues == INSERT
        ContentValues values = new ContentValues();
        values.put(LetrixSQLiteHelper.COLUMNA_ID_USUARIO, partida.getIdUsuario());
        values.put(LetrixSQLiteHelper.COLUMNA_ID_PALABRA, partida.getIdPalabra());
        values.put(LetrixSQLiteHelper.COLUMNA_NUMERO_INTENTOS, partida.getNumeroIntentos());
        // El resultado lo guardamos como 1 (ganamos) o 0 (perdimos) porque SQLite no tiene booleanos.
        values.put(LetrixSQLiteHelper.COLUMNA_RESULTADO, partida.isResultado() ? 1 : 0);
        // db.insert devuelve el id que le ha asignado la BD al nuevo registro
        long idGenerado = db.insert(LetrixSQLiteHelper.TABLA_PARTIDA, null, values);
        // Guardamos ese id en el objeto para tenerlo disponible durante el juego
        partida.setId((int) idGenerado);
    }

    // Actualizamos partida para terminar de jugar.
    // Cuando el usuario acaba sus intentos
    public void actualizar(Partida partida) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LetrixSQLiteHelper.COLUMNA_NUMERO_INTENTOS, partida.getNumeroIntentos());
        values.put(LetrixSQLiteHelper.COLUMNA_RESULTADO, partida.isResultado() ? 1 : 0);
        // UPDATE partida SET numeroIntentos = ?, resultado = ? WHERE id = ?
        db.update(LetrixSQLiteHelper.TABLA_PARTIDA, values,
                LetrixSQLiteHelper.COLUMNA_ID + "=?",
                new String[]{String.valueOf(partida.getId())});
    }

    // Metodo el cual trae a la partida por id
    public Partida obtenerPorId(int id) {

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;

        try {
            // SELECT * FROM partida WHERE id = ?
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

                        // Convertimos el 1/0 de SQLite de vuelta a boolean
                        cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_RESULTADO)) == 1
                );
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }
}
