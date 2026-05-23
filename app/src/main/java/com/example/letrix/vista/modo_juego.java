package com.example.letrix.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.R;

public class modo_juego extends AppCompatActivity {

    private ImageButton btnGeografia;
    private ImageButton btnCine;
    private ImageButton btnAnatomia;
    private ImageButton btnDeporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_juego);

        // Enlazar con el layout
        btnGeografia = findViewById(R.id.btnGeografia);
        btnCine = findViewById(R.id.btnCine);
        btnAnatomia = findViewById(R.id.btnAnatomia);
        btnDeporte = findViewById(R.id.btnDeporte);

        // Que hacer al pulsar los botones
        btnGeografia.setOnClickListener(v -> iniciarJuego("Geografía"));
        btnCine.setOnClickListener(v -> iniciarJuego("Cine"));
        btnAnatomia.setOnClickListener(v -> iniciarJuego("Anatomía"));
        btnDeporte.setOnClickListener(v -> iniciarJuego("Deporte"));
    }

    /**
     * Inicia la actividad del juego pasando la temática seleccionada
     */
    private void iniciarJuego(String tematica) {
        Toast.makeText(this, "Has seleccionado: " + tematica, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(modo_juego.this, juego.class);
        intent.putExtra("TEMATICA", tematica);
        startActivity(intent);
    }
}