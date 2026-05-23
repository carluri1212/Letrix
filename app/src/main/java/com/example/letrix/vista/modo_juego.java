package com.example.letrix.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        btnGeografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(modo_juego.this, "Has seleccionado: Geografía", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(modo_juego.this, MainActivity.class);
                intent.putExtra("TEMATICA", "Geografía");
                startActivity(intent);
            }
        });

        // Que hacer al pulsar el boton Cine
        btnCine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(modo_juego.this, "Has seleccionado: Cine", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(modo_juego.this, MainActivity.class);
                intent.putExtra("TEMATICA", "Cine");
                startActivity(intent);
            }
        });

        // Que hacer al pulsar el boton Anatomia
        btnAnatomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(modo_juego.this, "Has seleccionado: Anatomía", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(modo_juego.this, MainActivity.class);
                intent.putExtra("TEMATICA", "Anatomía");
                startActivity(intent);
            }
        });

        // Que hacer al pulsar el boton Deporte
        btnDeporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(modo_juego.this, "Has seleccionado: Deporte", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(modo_juego.this, MainActivity.class);
                intent.putExtra("TEMATICA", "Deporte");
                startActivity(intent);
            }
        });
    }
}