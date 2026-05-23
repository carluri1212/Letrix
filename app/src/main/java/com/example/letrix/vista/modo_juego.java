package com.example.letrix.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

        // botones de cada categoría
        btnGeografia.setOnClickListener(v -> abrirJuego("geografia"));
        btnCine.setOnClickListener(v -> abrirJuego("cine"));
        btnAnatomia.setOnClickListener(v -> abrirJuego("anatomia"));
        btnDeporte.setOnClickListener(v -> abrirJuego("deporte"));
    }

    // abre el juego con la categoría elegida
    private void abrirJuego(String categoria) {
        Intent intent = new Intent(modo_juego.this, Juego.class);
        intent.putExtra("CATEGORIA", categoria);
        startActivity(intent);
    }
}