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
// Pantalla donde se juega la partida.
// Recibe la categoría que eligió el usuario y arranca el juego con una palabra de esa categoría.
public class Juego extends AppCompatActivity {

    // 5 letras y 6 intentos
    private static final int LONGITUD_PALABRA = 5;
    private static final int MAX_INTENTOS = 6;

    // el controlador
    private ControladorPartida controladorPartida;

    // vistas
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

        // la categoría me llega del modo_juego
        String categoria = getIntent().getStringExtra("CATEGORIA");

        // saco el id por el nombre
        CategoriaDAO categoriaDAO = new CategoriaDAO(this);
        int idCategoria = categoriaDAO.buscarIdPorNombre(categoria);

        // pillo una palabra al azar de esa categoría
        PalabraDAO palabraDAO = new PalabraDAO(this);
        Palabra palabra = palabraDAO.obtenerPalabraAleatoria(String.valueOf(idCategoria));

        // por si no hay palabras
        if (palabra == null) {
            Toast.makeText(this, "No hay palabras en esta categoria", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        // arranco la partida con la palabra
        controladorPartida = new ControladorPartida();
        controladorPartida.iniciarPartida(palabra.getTexto());

        // elementos del layout
        tablero = findViewById(R.id.tablero);
        editIntento = findViewById(R.id.editIntento);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtMensaje = findViewById(R.id.txtMensaje);

        // al pulsar enviar
        btnEnviar.setOnClickListener(v -> enviarIntento());
    }

    /**
     * Se ejecuta cuando el jugador pulsa Enviar.
     */
    private void enviarIntento() {
        String intento = editIntento.getText().toString().trim().toUpperCase();

        // que tenga 5 letras
        if (intento.length() != LONGITUD_PALABRA) {
            Toast.makeText(this, "La palabra debe tener " + LONGITUD_PALABRA + " letras",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // proceso el intento
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
            txtMensaje.setText("¡PERDISTE! La palabra era: " + controladorPartida.getPalabraObjetivo());
            btnEnviar.setEnabled(false);
            editIntento.setEnabled(false);
        }
    }

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

