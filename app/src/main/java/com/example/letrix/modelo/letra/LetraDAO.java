package com.example.letrix.modelo.letra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.letrix.modelo.LetrixSQLiteHelper;
import java.util.ArrayList;
import java.util.List;

public class LetraDAO {

    private final LetrixSQLiteHelper helper;

    public LetraDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    // Guardamos una letra que el usuario introduzca
    public void insertar(Letra letra) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LetrixSQLiteHelper.COLUMNA_ID_PARTIDA, letra.getIdPartida());
        values.put(LetrixSQLiteHelper.COLUMNA_CARACTER, String.valueOf(letra.getCaracter()));
        values.put(LetrixSQLiteHelper.COLUMNA_POSICION, letra.getPosicion());
        values.put(LetrixSQLiteHelper.COLUMNA_ESTADO, letra.getEstado().name());
        db.insert(LetrixSQLiteHelper.TABLA_LETRA, null, values);
    }

    // Obtenemos todas las letras de la partida (para pintar el tablero)
    public List<Letra> obtenerPorPartida(int idPartida) {
        List<Letra> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(LetrixSQLiteHelper.TABLA_LETRA, null,
                    LetrixSQLiteHelper.COLUMNA_ID_PARTIDA + "=?",
                    new String[]{String.valueOf(idPartida)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    lista.add(new Letra(
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_PARTIDA)),
                            cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_CARACTER)).charAt(0),
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_POSICION)),
                            EstadoLetra.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ESTADO)))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return lista;
    }
}