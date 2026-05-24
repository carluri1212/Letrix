package com.example.letrix.vista;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.Controlador.ControladorPartida;
import com.example.letrix.R;
import com.example.letrix.modelo.letra.EstadoLetra;

public class Juego extends AppCompatActivity {

    TextView txtMensaje;
    EditText editIntento;
    Button btnEnviar;

    TextView tv00, tv01, tv02, tv03, tv04;
    TextView tv10, tv11, tv12, tv13, tv14;
    TextView tv20, tv21, tv22, tv23, tv24;
    TextView tv30, tv31, tv32, tv33, tv34;
    TextView tv40, tv41, tv42, tv43, tv44;
    TextView tv50, tv51, tv52, tv53, tv54;

    ControladorPartida controladorPartida;
    int filaActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        String categoria = getIntent().getStringExtra("CATEGORIA");

        controladorPartida = new ControladorPartida();

        // TODO: cuando tu compañero añada iniciarPartidaPorCategoria al
        // ControladorPartida, sustituye la línea de abajo por:
        // controladorPartida.iniciarPartidaPorCategoria(this, categoria);
        controladorPartida.iniciarPartida("PERRO");

        txtMensaje = findViewById(R.id.txtMensaje);
        editIntento = findViewById(R.id.editIntento);
        btnEnviar = findViewById(R.id.btnEnviar);

        tv00 = findViewById(R.id.tv00); tv01 = findViewById(R.id.tv01);
        tv02 = findViewById(R.id.tv02); tv03 = findViewById(R.id.tv03);
        tv04 = findViewById(R.id.tv04);

        tv10 = findViewById(R.id.tv10); tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12); tv13 = findViewById(R.id.tv13);
        tv14 = findViewById(R.id.tv14);

        tv20 = findViewById(R.id.tv20); tv21 = findViewById(R.id.tv21);
        tv22 = findViewById(R.id.tv22); tv23 = findViewById(R.id.tv23);
        tv24 = findViewById(R.id.tv24);

        tv30 = findViewById(R.id.tv30); tv31 = findViewById(R.id.tv31);
        tv32 = findViewById(R.id.tv32); tv33 = findViewById(R.id.tv33);
        tv34 = findViewById(R.id.tv34);

        tv40 = findViewById(R.id.tv40); tv41 = findViewById(R.id.tv41);
        tv42 = findViewById(R.id.tv42); tv43 = findViewById(R.id.tv43);
        tv44 = findViewById(R.id.tv44);

        tv50 = findViewById(R.id.tv50); tv51 = findViewById(R.id.tv51);
        tv52 = findViewById(R.id.tv52); tv53 = findViewById(R.id.tv53);
        tv54 = findViewById(R.id.tv54);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (controladorPartida.partidaTerminada()) {
                    Toast.makeText(Juego.this, "La partida ha terminado", Toast.LENGTH_SHORT).show();
                    return;
                }

                String intento = editIntento.getText().toString().trim().toUpperCase();

                if (intento.isEmpty()) {
                    Toast.makeText(Juego.this, "Escribe una palabra", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (intento.length() != 5) {
                    Toast.makeText(Juego.this, "La palabra debe tener 5 letras", Toast.LENGTH_SHORT).show();
                    return;
                }

                EstadoLetra[] resultado = controladorPartida.procesarIntento(intento);

                if (filaActual == 0) {
                    tv00.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv00.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv00.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv00.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv01.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv01.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv01.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv01.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv02.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv02.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv02.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv02.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv03.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv03.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv03.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv03.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv04.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv04.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv04.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv04.setBackgroundColor(Color.parseColor("#9E9E9E")); }

                } else if (filaActual == 1) {
                    tv10.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv10.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv10.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv10.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv11.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv11.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv11.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv11.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv12.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv12.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv12.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv12.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv13.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv13.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv13.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv13.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv14.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv14.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv14.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv14.setBackgroundColor(Color.parseColor("#9E9E9E")); }

                } else if (filaActual == 2) {
                    tv20.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv20.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv20.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv20.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv21.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv21.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv21.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv21.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv22.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv22.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv22.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv22.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv23.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv23.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv23.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv23.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv24.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv24.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv24.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv24.setBackgroundColor(Color.parseColor("#9E9E9E")); }

                } else if (filaActual == 3) {
                    tv30.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv30.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv30.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv30.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv31.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv31.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv31.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv31.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv32.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv32.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv32.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv32.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv33.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv33.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv33.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv33.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv34.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv34.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv34.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv34.setBackgroundColor(Color.parseColor("#9E9E9E")); }

                } else if (filaActual == 4) {
                    tv40.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv40.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv40.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv40.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv41.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv41.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv41.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv41.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv42.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv42.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv42.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv42.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv43.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv43.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv43.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv43.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv44.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv44.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv44.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv44.setBackgroundColor(Color.parseColor("#9E9E9E")); }

                } else if (filaActual == 5) {
                    tv50.setText(String.valueOf(intento.charAt(0)));
                    if (resultado[0] == EstadoLetra.CORRECTA) { tv50.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[0] == EstadoLetra.PRESENTE) { tv50.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv50.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv51.setText(String.valueOf(intento.charAt(1)));
                    if (resultado[1] == EstadoLetra.CORRECTA) { tv51.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[1] == EstadoLetra.PRESENTE) { tv51.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv51.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv52.setText(String.valueOf(intento.charAt(2)));
                    if (resultado[2] == EstadoLetra.CORRECTA) { tv52.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[2] == EstadoLetra.PRESENTE) { tv52.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv52.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv53.setText(String.valueOf(intento.charAt(3)));
                    if (resultado[3] == EstadoLetra.CORRECTA) { tv53.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[3] == EstadoLetra.PRESENTE) { tv53.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv53.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                    tv54.setText(String.valueOf(intento.charAt(4)));
                    if (resultado[4] == EstadoLetra.CORRECTA) { tv54.setBackgroundColor(Color.parseColor("#4CAF50")); }
                    else if (resultado[4] == EstadoLetra.PRESENTE) { tv54.setBackgroundColor(Color.parseColor("#FFEB3B")); }
                    else { tv54.setBackgroundColor(Color.parseColor("#9E9E9E")); }
                }

                filaActual++;
                editIntento.setText("");

                if (controladorPartida.partidaGanada()) {
                    txtMensaje.setText("¡HAS GANADO!");
                    Toast.makeText(Juego.this, "¡Enhorabuena!", Toast.LENGTH_LONG).show();
                } else if (controladorPartida.partidaPerdida()) {
                    txtMensaje.setText("GAME OVER - La palabra era: " + controladorPartida.getPalabraObjetivo());
                    Toast.makeText(Juego.this, "La palabra era: " + controladorPartida.getPalabraObjetivo(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}