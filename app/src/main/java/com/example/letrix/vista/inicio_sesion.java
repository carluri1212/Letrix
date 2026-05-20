package com.example.letrix.vista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

        // Que hacer al pulsar el boton
        btnIniciarSesion.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString();
            String contrasena = editContrasena.getText().toString();
            ResultadoOperacion resultado = controladorUsuario.iniciarSesion(usuario, contrasena);
            Toast.makeText(this, resultado.getMensaje(), Toast.LENGTH_SHORT).show();
        });
    }
}