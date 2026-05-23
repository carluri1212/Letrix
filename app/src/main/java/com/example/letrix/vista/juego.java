package com.example.letrix.vista;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.R;

public class juego extends AppCompatActivity {

    private TextView txtCategoria;
    private TextView txtIntentos;
    private TextView txtMensajeFinal;
    private TextView[][] matrizTextViews = new TextView[6][5];
    private EditText editPalabra;
    private Button btnComprobar;

    private int intentosRestantes = 6;
    private int filaActual = 0;
    private String palabraCorrecta = "PERRO"; // Palabra de prueba

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // Recibir la temática de la actividad anterior
        String tematica = getIntent().getStringExtra("TEMATICA");

        // Enlazar componentes básicos
        txtCategoria = findViewById(R.id.txtCategoria);
        txtIntentos = findViewById(R.id.txtIntentos);
        txtMensajeFinal = findViewById(R.id.txtMensajeFinal);
        editPalabra = findViewById(R.id.editPalabra);
        btnComprobar = findViewById(R.id.btnComprobar);

        // Enlazar la cuadrícula de 30 TextViews (tv00 a tv54)
        vincularMatriz();

        // Mostrar la temática seleccionada
        if (tematica != null) {
            txtCategoria.setText("Categoría: " + tematica);
        }

        actualizarIntentos();

        // Que hacer al pulsar el botón Comprobar
        btnComprobar.setOnClickListener(v -> {
            String palabraIngresada = editPalabra.getText().toString().trim().toUpperCase();

            if (palabraIngresada.length() != 5) {
                Toast.makeText(this, "La palabra debe tener 5 letras", Toast.LENGTH_SHORT).show();
                return;
            }

            if (filaActual < 6) {
                // Escribir la palabra en la fila correspondiente
                for (int j = 0; j < 5; j++) {
                    matrizTextViews[filaActual][j].setText(String.valueOf(palabraIngresada.charAt(j)));
                }

                // Comprobar si ha ganado
                if (palabraIngresada.equals(palabraCorrecta)) {
                    finalizarJuego(true);
                    return;
                }

                filaActual++;
                intentosRestantes--;
                actualizarIntentos();
                editPalabra.setText("");

                // Comprobar si ha perdido (se han agotado los intentos)
                if (intentosRestantes == 0) {
                    finalizarJuego(false);
                }
            }
        });
    }

    /**
     * Muestra el mensaje final y desactiva los controles
     * @param ganado true si el jugador ha acertado, false si ha agotado intentos
     */
    private void finalizarJuego(boolean ganado) {
        txtMensajeFinal.setVisibility(View.VISIBLE);
        btnComprobar.setEnabled(false);
        editPalabra.setEnabled(false);

        if (ganado) {
            txtMensajeFinal.setText("¡HAS GANADO!");
            txtMensajeFinal.setTextColor(Color.parseColor("#4CAF50")); // Verde
        } else {
            txtMensajeFinal.setText("GAME OVER");
            txtMensajeFinal.setTextColor(Color.parseColor("#F44336")); // Rojo
            Toast.makeText(this, "La palabra era: " + palabraCorrecta, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Vincula los 30 TextViews del layout con la matriz en Java
     */
    private void vincularMatriz() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                String idName = "tv" + i + j;
                int resId = getResources().getIdentifier(idName, "id", getPackageName());
                matrizTextViews[i][j] = findViewById(resId);
            }
        }
    }

    /**
     * Actualiza el contador visual de intentos
     */
    private void actualizarIntentos() {
        txtIntentos.setText("Intentos restantes: " + intentosRestantes);
    }
}