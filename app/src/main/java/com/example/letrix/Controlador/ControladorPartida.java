package com.example.letrix.Controlador;

import com.example.letrix.modelo.letra.EstadoLetra;

import java.util.HashMap;
public class ControladorPartida {
    // max 6 intentos
    private static final int MAX_INTENTOS = 6;

    // palabra a adivinar
    private String palabraObjetivo;

    // intentos usados
    private int intentosUsados;

    // true si ganó
    private boolean ganada;

    public void iniciarPartida(String objetivo) {
        this.palabraObjetivo = objetivo.trim().toUpperCase();
        this.intentosUsados =0;
        this.ganada=false;
    }

    public EstadoLetra[] procesarIntento(String intento) {
        // 1. Normalizar el intento (mayusculas, sin espacios).
        String intentoNormalizado = intento.trim().toUpperCase();

        // 2. Comparar el intento con la palabra objetivo (paso 1 -> 3 del algoritmo).
        EstadoLetra[] resultado = compararPalabras(intentoNormalizado, palabraObjetivo);

        // 3. Sumar un intento usado.
        intentosUsados++;

        // 4. Comprobar si ha acertado.
        if(intentoNormalizado.equalsIgnoreCase(palabraObjetivo)){
            ganada=true;
        }
        // 5. Devolver el resultado para que la vista lo pinte.
        return resultado;
    }

    /**
     * @return true si el jugador ha ganado.
     */
    public boolean partidaGanada() {
        return ganada;
    }

    /**
     * @return true si la partida ha terminado: porque ha ganado, o porque
     *         se han acabado los intentos.
     */
    public boolean partidaTerminada() {
        return (ganada || intentosUsados >= MAX_INTENTOS);
    }

    /**
     * @return true si la partida ha terminado y NO se ha ganado.
     */
    public boolean partidaPerdida() {

        return (partidaTerminada() && !partidaGanada());
    }

    /**
     * @return cuantos intentos le quedan al jugador.
     */
    public int getIntentosRestantes() {

        return MAX_INTENTOS-intentosUsados;
    }

    /**
     * @return la palabra que habia que adivinar. Util para mostrarla al
     *         jugador cuando pierde.
     */
    public String getPalabraObjetivo() {
        return palabraObjetivo;
    }
    private EstadoLetra[] compararPalabras(String intento, String objetivo) {
        EstadoLetra[] estados = new EstadoLetra[intento.length()];

        // ===== PASO 1: construir el inventario de letras del objetivo =====
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

        // ===== PASO 2: pasada de los verdes (CORRECTA) =====
        for (int i = 0; i < intento.length(); i++) {
            char letraIntento = intento.charAt(i);
            char letraObjetivo = objetivo.charAt(i);

            if (letraIntento == letraObjetivo) {
                // Es verde: marcar la posicion como CORRECTA.
                estados[i] = EstadoLetra.CORRECTA;

                // Descontar esa letra del inventario (ya la hemos "usado").
                int veces = inventario.get(letraIntento);
                inventario.put(letraIntento, veces - 1);
            }
        }

        // ===== PASO 3: pasada de amarillos y grises =====
        for (int i = 0; i < intento.length(); i++) {
            // Si esta posicion YA es verde, la saltamos: nada que hacer.
            if (estados[i] == EstadoLetra.CORRECTA) {
                continue;
            }

            char letraIntento = intento.charAt(i);

            // ¿Queda esa letra disponible en el inventario?
            // OJO: hay que mirar tanto que exista como que su valor sea > 0.
            if (inventario.containsKey(letraIntento) && inventario.get(letraIntento) >0) {
                // Es amarilla.
                estados[i] =EstadoLetra.PRESENTE;

                // Descontar del inventario (como en el paso 2).
                int veces = inventario.get(letraIntento);
                inventario.put(letraIntento, veces - 1);
            } else {
                // No queda: es gris.
                estados[i] =EstadoLetra.AUSENTE;
            }
        }

        return estados;
    }
}
