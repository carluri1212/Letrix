package com.example.letrix.Controlador;

import android.content.Context;

import com.example.letrix.modelo.DAOException;
import com.example.letrix.modelo.Usuario;
import com.example.letrix.modelo.UsuarioDAO;
import com.example.letrix.modelo.UsuarioDAOInterfaz;

public class ControladorUsuario {

    private static final int LONGITUD_MINIMA_CONTRASENA = 4;

    private final UsuarioDAOInterfaz usuarioDAO;

    // El controlador recibe el Context (el "entorno" de Android, que en
    // la practica es la Activity que lo crea) y con el crea el DAO por
    // dentro. Asi la vista no tiene que saber nada del DAO.
    public ControladorUsuario(Context context) {
        this.usuarioDAO = new UsuarioDAO(context);
    }

    public ResultadoOperacion registrarUsuario(String usuario, String contrasena) {
        if (usuario == null || usuario.isEmpty()) {
            return new ResultadoOperacion(false, "El jugador no puede estar vacio");
        }
        if (contrasena == null || contrasena.isEmpty()) {
            return new ResultadoOperacion(false, "La contrasena no puede estar vacia");
        }
        if (contrasena.length() < LONGITUD_MINIMA_CONTRASENA) {
            return new ResultadoOperacion(false,
                    "La contrasena debe tener al menos "
                            + LONGITUD_MINIMA_CONTRASENA + " caracteres");
        }

        String nombre = usuario.trim();

        try {
            Usuario usuarioExistente = usuarioDAO.buscarPorNombre(nombre);
            if (usuarioExistente != null) {
                return new ResultadoOperacion(false, "El jugador ya existe");
            }

            Usuario nuevoUsuario = new Usuario(nombre, contrasena);
            usuarioDAO.registrar(nuevoUsuario);

            return new ResultadoOperacion(true, "Usuario registrado correctamente");
        } catch (DAOException e) {
            return new ResultadoOperacion(false, "Error al acceder a la base de datos");
        }
    }

    public ResultadoOperacion iniciarSesion(String usuario, String contrasena) {
        if (usuario == null || usuario.isEmpty()) {
            return new ResultadoOperacion(false, "El jugador no puede estar vacio");
        }
        if (contrasena == null || contrasena.isEmpty()) {
            return new ResultadoOperacion(false, "La contrasena no puede estar vacia");
        }

        String nombre = usuario.trim();
        try {
            Usuario usuarioExistente = usuarioDAO.buscarPorNombre(nombre);
            if (usuarioExistente == null) {
                return new ResultadoOperacion(false, "El jugador no existe");
            }
            if (!usuarioExistente.getContrasena().equals(contrasena)) {
                return new ResultadoOperacion(false, "La contrasena es incorrecta");
            }
            return new ResultadoOperacion(true, "Inicio de sesion correcto");
        } catch (DAOException e) {
            return new ResultadoOperacion(false, "Error al acceder a la base de datos");
        }
    }
}