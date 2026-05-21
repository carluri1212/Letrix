package com.example.letrix.Controlador;

import java.util.HashMap;
public class ControladorPartida {
    private EstadoLetra[] compararPalabras(String intento, String objetivo) {
        EstadoLetra[] estados = new EstadoLetra[intento.length()];

        // ===== PASO 1: construir el inventario de letras del objetivo =====
        HashMap<Character, Integer> inventario = new HashMap<>();

        for (int i = 0; i < objetivo.length(); i++) {
            char letra = objetivo.charAt(i);

            // ¿Esta letra ya esta en el inventario?
            if (inventario.containsKey(letra)) {
                // Si ya estaba: leer cuanto valia, sumarle 1 y volver a guardarlo.
                int veces =inventario.get(letra);
                inventario.put(letra, veces+1);
            } else {
                inventario.put(letra, 1);
            }
        }

        // Aqui podrias hacer un System.out.println(inventario); para verlo.

        // ===== PASO 2: pasada de los verdes  (vendra despues) =====
        // ===== PASO 3: pasada de amarillos y grises (vendra despues) =====

        return estados;
    }
}
