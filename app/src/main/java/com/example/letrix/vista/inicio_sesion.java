package com.example.letrix.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.Controlador.ControladorFichero;
import com.example.letrix.Controlador.ControladorUsuario;
import com.example.letrix.Controlador.ResultadoOperacion;
import com.example.letrix.R;

public class inicio_sesion extends AppCompatActivity {

    private ControladorUsuario controladorUsuario;
    private EditText editUsuario;
    private EditText editContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        controladorUsuario = new ControladorUsuario(this);

        // Cargamos el diccionario la primera vez que arranca la app.
        ControladorFichero controladorFichero = new ControladorFichero(this);
        controladorFichero.cargarDiccionarioSiVacio();

        editUsuario = findViewById(R.id.editUsuario);
        editContrasena = findViewById(R.id.editContrasena);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        Button btnRegistrate = findViewById(R.id.button);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editUsuario.getText().toString();
                String contrasena = editContrasena.getText().toString();

                ResultadoOperacion resultado = controladorUsuario.iniciarSesion(usuario, contrasena);
                Toast.makeText(inicio_sesion.this, resultado.getMensaje(), Toast.LENGTH_SHORT).show();

                if (resultado.isExito()) {
                    Intent intent = new Intent(inicio_sesion.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio_sesion.this, registro.class);
                startActivity(intent);
            }
        });
    }
}