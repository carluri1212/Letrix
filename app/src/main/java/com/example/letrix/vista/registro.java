package com.example.letrix.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letrix.Controlador.ControladorUsuario;
import com.example.letrix.Controlador.ResultadoOperacion;
import com.example.letrix.R;

public class registro extends AppCompatActivity {

    private ControladorUsuario controladorUsuario;
    private EditText editUsuario;
    private EditText editContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        controladorUsuario = new ControladorUsuario(this);

        editUsuario = findViewById(R.id.editRegistroUsuario);
        editContrasena = findViewById(R.id.editRegistroContrasena);
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editUsuario.getText().toString();
                String contrasena = editContrasena.getText().toString();

                ResultadoOperacion resultado = controladorUsuario.registrarUsuario(usuario, contrasena);
                Toast.makeText(registro.this, resultado.getMensaje(), Toast.LENGTH_SHORT).show();

                if (resultado.isExito()) {
                    finish();
                }
            }
        });
    }
}