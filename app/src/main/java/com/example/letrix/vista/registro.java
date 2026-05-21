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
import com.example.letrix.modelo.UsuarioDAOInterfaz;

public class registro extends AppCompatActivity {

    private ControladorUsuario controladorUsuario;
    private EditText editUsuario;
    private EditText editContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Montar la cadena Vista -> Controlador -> DAO
        UsuarioDAOInterfaz usuarioDAO = new UsuarioDAO(this);
        controladorUsuario = new ControladorUsuario(usuarioDAO);

        // Enlazar con el layout
        editUsuario = findViewById(R.id.editRegistroUsuario);
        editContrasena = findViewById(R.id.editRegistroContrasena);
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        // Que hacer al pulsar el boton
        btnCrearCuenta.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString();
            String contrasena = editContrasena.getText().toString();

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            ResultadoOperacion resultado = controladorUsuario.registrarUsuario(usuario, contrasena);
            Toast.makeText(this, resultado.getMensaje(), Toast.LENGTH_SHORT).show();

            if (resultado.isExito()) {
                // Si el registro es correcto, volvemos a la pantalla de login
                finish();
            }
        });
    }
}