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

        // Que hacer al pulsar el boton Geografia
        btnGeografia.setOnClickListener(v -> {
            iniciarJuego("Geografía");
        });

        // Que hacer al pulsar el boton Cine
        btnCine.setOnClickListener(v -> {
            iniciarJuego("Cine");
        });

        // Que hacer al pulsar el boton Anatomia
        btnAnatomia.setOnClickListener(v -> {
            iniciarJuego("Anatomía");
        });

        // Que hacer al pulsar el boton Deporte
        btnDeporte.setOnClickListener(v -> {
            iniciarJuego("Deporte");
        });
    }

    /**
     * Metodo para iniciar la actividad principal del juego pasando la tematica seleccionada
     * @param tematica Nombre del tema elegido
     */
    private void iniciarJuego(String tematica) {
        Toast.makeText(this, "Has seleccionado: " + tematica, Toast.LENGTH_SHORT).show();
        
        // Pasamos a la actividad principal (MainActivity) enviando la tematica
        // En MainActivity podras recibirla con getIntent().getStringExtra("TEMATICA")
        Intent intent = new Intent(modo_juego.this, MainActivity.class);
        intent.putExtra("TEMATICA", tematica);
        startActivity(intent);
    }
}