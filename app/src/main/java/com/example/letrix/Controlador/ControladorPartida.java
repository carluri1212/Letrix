package com.example.letrix.Controlador;

import android.content.Context;

import com.example.letrix.modelo.categoria.CategoriaDAO;
import com.example.letrix.modelo.letra.EstadoLetra;
import com.example.letrix.modelo.palabra.Palabra;
import com.example.letrix.modelo.palabra.PalabraDAO;

import java.util.HashMap;

public class ControladorPartida {

    private static final int MAX_INTENTOS = 6;

    private String palabraObjetivo;
    private int intentosUsados;
    private boolean ganada;

    // Pide una palabra aleatoria al DAO según la categoría elegida
    // y arranca la partida. Devuelve false si no hay palabras.
    public boolean iniciarPartidaPorCategoria(Context context, String categoria) {
        CategoriaDAO categoriaDAO = new CategoriaDAO(context);
        int idCategoria = categoriaDAO.buscarIdPorNombre(categoria);

        PalabraDAO palabraDAO = new PalabraDAO(context);
        Palabra palabra = palabraDAO.obtenerPalabraAleatoria(String.valueOf(idCategoria));

        if (palabra == null) {
            return false;
        }
        iniciarPartida(palabra.getTexto());
        return true;
    }

    public void iniciarPartida(String objetivo) {
        this.palabraObjetivo = objetivo.trim().toUpperCase();
        this.intentosUsados = 0;
        this.ganada = false;
    }

    public EstadoLetra[] procesarIntento(String intento) {
        String intentoNormalizado = intento.trim().toUpperCase();

        EstadoLetra[] resultado = compararPalabras(intentoNormalizado, palabraObjetivo);

        intentosUsados++;

        if (intentoNormalizado.equalsIgnoreCase(palabraObjetivo)) {
            ganada = true;
        }
        return resultado;
    }

    public boolean partidaGanada() {
        return ganada;
    }

    public boolean partidaTerminada() {
        return (ganada || intentosUsados >= MAX_INTENTOS);
    }

    public boolean partidaPerdida() {
        return (partidaTerminada() && !partidaGanada());
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentosUsados;
    }

    public String getPalabraObjetivo() {
        return palabraObjetivo;
    }

    // Compara el intento con la palabra a adivinar y devuelve el estado
    // de cada letra (verde, amarilla o gris). Hace dos pasadas para que
    // las letras repetidas no fallen.
    private EstadoLetra[] compararPalabras(String intento, String objetivo) {
        EstadoLetra[] estados = new EstadoLetra[intento.length()];

        // 1. Cuento las letras que tiene la palabra objetivo
        HashMap<Character, Integer> inventario = new HashMap<>();

        for (int i = 0; i < objetivo.length(); i++) {
            char letra = objetivo.charAt(i);

            if (inventario.containsKey(letra)) {
                int veces = inventario.get(letra);
                inventario.put(letra, veces + 1);
            } else {
                inventario.put(letra, 1);
            }
        }

        // 2. Marco las letras verdes (las que están en su sitio)
        for (int i = 0; i < intento.length(); i++) {
            char letraIntento = intento.charAt(i);
            char letraObjetivo = objetivo.charAt(i);

            if (letraIntento == letraObjetivo) {
                estados[i] = EstadoLetra.CORRECTA;

                int veces = inventario.get(letraIntento);
                inventario.put(letraIntento, veces - 1);
            }
        }

        // 3. Marco las amarillas y las grises
        for (int i = 0; i < intento.length(); i++) {
            if (estados[i] == EstadoLetra.CORRECTA) {
                continue;
            }

            char letraIntento = intento.charAt(i);

            if (inventario.containsKey(letraIntento) && inventario.get(letraIntento) > 0) {
                estados[i] = EstadoLetra.PRESENTE;

                int veces = inventario.get(letraIntento);
                inventario.put(letraIntento, veces - 1);
            } else {
                estados[i] = EstadoLetra.AUSENTE;
            }
        }

        return estados;
    }
}