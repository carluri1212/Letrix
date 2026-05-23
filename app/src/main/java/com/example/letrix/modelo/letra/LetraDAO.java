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

    // Metodo que guarda una letra en la BBDD cada vez que el Jugador introduce una letra en el juego.
    // Se solo 1 vez por intento 0/5.
    public void insertar(Letra letra) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        // La letra esta a una partida concreta.
        values.put(LetrixSQLiteHelper.COLUMNA_ID_PARTIDA, letra.getIdPartida());
        // Guardamos el caracter como String porque SQLite no tiene tipo char.
        values.put(LetrixSQLiteHelper.COLUMNA_CARACTER, String.valueOf(letra.getCaracter()));
        // La posición (0-4) nos dice en qué casilla del tablero va esta letra.
        values.put(LetrixSQLiteHelper.COLUMNA_POSICION, letra.getPosicion());
        // El estado lo guardamos como texto; CORRECTA,PRESENTE o AUSENTE.
        // Así la vista sabe con qué color pintar cada casilla (verde, naranja, rojo).
        values.put(LetrixSQLiteHelper.COLUMNA_ESTADO, letra.getEstado().name());

        db.insert(LetrixSQLiteHelper.TABLA_LETRA, null, values);
    }

    // Metodo que devuelve todas las letras de una partida
    // La vista lo usa para pintar el tablero completo con todos los intentos anteriores
    public List<Letra> obtenerPorPartida(int idPartida) {

        List<Letra> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;

        try {
            // SELECT * FROM letra WHERE idPartida = ?
            cursor = db.query(LetrixSQLiteHelper.TABLA_LETRA, null,
                    LetrixSQLiteHelper.COLUMNA_ID_PARTIDA + "=?",
                    new String[]{String.valueOf(idPartida)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    lista.add(new Letra(
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID_PARTIDA)),
                            // Convertimos el String  a char cogiendo el primer carácter
                            cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_CARACTER)).charAt(0),
                            cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_POSICION)),
                            // Convertimos el String de vuelta al enum EstadoLetra
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