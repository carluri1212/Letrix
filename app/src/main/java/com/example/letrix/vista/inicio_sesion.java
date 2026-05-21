package com.example.letrix.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.Controlador.ControladorUsuario;
import com.example.letrix.Controlador.ResultadoOperacion;
import com.example.letrix.R;
import com.example.letrix.modelo.UsuarioDAO;

public class inicio_sesion extends AppCompatActivity {

    private ControladorUsuario controladorUsuario;
    private EditText editUsuario;
    private EditText editContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        // Montar la cadena Vista -> Controlador -> DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        controladorUsuario = new ControladorUsuario(usuarioDAO);

        // Enlazar con el layout
        editUsuario = findViewById(R.id.editUsuario);
        editContrasena = findViewById(R.id.editContrasena);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView txtRegistrate = findViewById(R.id.textView2);

        // Que hacer al pulsar el boton Iniciar Sesión
        btnIniciarSesion.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString();
            String contrasena = editContrasena.getText().toString();
            
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            ResultadoOperacion resultado = controladorUsuario.iniciarSesion(usuario, contrasena);
            Toast.makeText(this, resultado.getMensaje(), Toast.LENGTH_SHORT).show();

            if (resultado.isExito()) {
                // Ir a la pantalla principal del juego
                Intent intent = new Intent(inicio_sesion.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cerramos esta actividad para que no pueda volver atrás al login
            }
        });

        // Navegación a la pantalla de registro
        txtRegistrate.setOnClickListener(v -> {
            Intent intent = new Intent(inicio_sesion.this, registro.class);
            startActivity(intent);
        });
    }
}