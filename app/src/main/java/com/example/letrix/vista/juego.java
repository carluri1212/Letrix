package com.example.letrix.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.letrix.Controlador.ControladorPartida;
import com.example.letrix.R;
import com.example.letrix.modelo.categoria.CategoriaDAO;
import com.example.letrix.modelo.letra.EstadoLetra;
import com.example.letrix.modelo.palabra.Palabra;
import com.example.letrix.modelo.palabra.PalabraDAO;

    /**
     * Pantalla del JUEGO en sí.
     *
     * Recibe la categoria desde modo_juego (por Intent), pide una palabra
     * al DAO de Miguel, crea el ControladorPartida (Carlos) y maneja los
     * intentos del jugador.
     */
    public class Juego extends AppCompatActivity {

        // Constantes del juego: 5 letras x 6 intentos (como Wordle).
        private static final int LONGITUD_PALABRA = 5;
        private static final int MAX_INTENTOS = 6;

        // Logica del juego.
        private ControladorPartida controladorPartida;

        // Elementos de la pantalla.
        private GridLayout tablero;
        private EditText editIntento;
        private Button btnEnviar;
        private TextView txtMensaje;

        // Fila actual del tablero (0..5). Cada intento "pinta" una fila.
        private int filaActual = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_juego);

            // 1. Recoger la categoria que viene desde modo_juego.
            String categoria = getIntent().getStringExtra("CATEGORIA");

            // 2. Conseguir el id de la categoria con el DAO de Miguel.
            CategoriaDAO categoriaDAO = new CategoriaDAO(this);
            int idCategoria = categoriaDAO.buscarIdPorNombre(categoria);

            // 3. Pedir una palabra aleatoria de esa categoria.
            PalabraDAO palabraDAO = new PalabraDAO(this);
            Palabra palabra = palabraDAO.obtenerPalabraAleatoria(String.valueOf(idCategoria));

            // Si no hay palabras (no deberia pasar, pero por si acaso).
            if (palabra == null) {
                Toast.makeText(this, "No hay palabras en esta categoria", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            // 4. Crear el controlador de partida y arrancar la partida.
            controladorPartida = new ControladorPartida();
            controladorPartida.iniciarPartida(palabra.getTexto());

            // 5. Enlazar elementos de la pantalla.
            tablero = findViewById(R.id.tablero);
            editIntento = findViewById(R.id.editIntento);
            btnEnviar = findViewById(R.id.btnEnviar);
            txtMensaje = findViewById(R.id.txtMensaje);

            // 6. Que hacer al pulsar "Enviar".
            btnEnviar.setOnClickListener(v -> enviarIntento());
        }

        /**
         * Se ejecuta cuando el jugador pulsa Enviar.
         */
        private void enviarIntento() {
            String intento = editIntento.getText().toString().trim().toUpperCase();

            // Validar la longitud antes de enviar.
            if (intento.length() != LONGITUD_PALABRA) {
                Toast.makeText(this, "La palabra debe tener " + LONGITUD_PALABRA + " letras",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Pedir al controlador que procese el intento.
            EstadoLetra[] resultado = controladorPartida.procesarIntento(intento);

            // Pintar el resultado en la fila actual del tablero.
            pintarFila(filaActual, intento, resultado);
            filaActual++;

            // Limpiar el campo de texto para el siguiente intento.
            editIntento.setText("");

            // Comprobar si la partida termino.
            if (controladorPartida.partidaGanada()) {
                txtMensaje.setText("¡GANASTE! 🎉");
                btnEnviar.setEnabled(false);
                editIntento.setEnabled(false);
            } else if (controladorPartida.partidaTerminada()) {
                // Se acabaron los intentos sin acertar.
                txtMensaje.setText("¡PERDISTE! La palabra era: "
                        + controladorPartida.getPalabraObjetivo());
                btnEnviar.setEnabled(false);
                editIntento.setEnabled(false);
            }
        }

        /**
         * Pinta una fila del tablero con las letras y sus colores.
         *
         * El tablero es un GridLayout con 5x6 = 30 TextView (uno por casilla).
         * Para acceder a la casilla (fila, columna) se hace:
         *    tablero.getChildAt(fila * 5 + columna)
         */
        private void pintarFila(int fila, String intento, EstadoLetra[] estados) {
            for (int col = 0; col < LONGITUD_PALABRA; col++) {
                TextView casilla = (TextView) tablero.getChildAt(fila * LONGITUD_PALABRA + col);
                casilla.setText(String.valueOf(intento.charAt(col)));

                // Color de fondo segun el estado.
                int color;
                switch (estados[col]) {
                    case CORRECTA:
                        color = ContextCompat.getColor(this, android.R.color.holo_green_dark);
                        break;
                    case PRESENTE:
                        color = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
                        break;
                    default: // AUSENTE
                        color = ContextCompat.getColor(this, android.R.color.darker_gray);
                        break;
                }
                casilla.setBackgroundColor(color);
            }
        }
    }

