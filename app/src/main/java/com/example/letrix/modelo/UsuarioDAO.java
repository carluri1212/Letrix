package com.example.letrix.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO implements UsuarioDAOInterfaz  {

    private final LetrixSQLiteHelper helper; //Activamos la base de datos.

    public UsuarioDAO(Context context) {
        this.helper = new LetrixSQLiteHelper(context);
    }

    @Override
    public void registrar(Usuario usuario) throws DAOException {
        //Escribimos dentro de la base de datos con este comando:
        SQLiteDatabase db = helper.getWritableDatabase();
        //ContentValues == Insert
        ContentValues valores = new ContentValues();
        //Obtenemos los resultados del usuario y los colocamos en valores.
        valores.put(LetrixSQLiteHelper.COLUMNA_USUARIO, usuario.getUsuario());
        valores.put(LetrixSQLiteHelper.COLUMNA_CONTRASENA, usuario.getContrasena());

        long idGenerado = db.insert(LetrixSQLiteHelper.TABLA_USUARIO, null, valores);

        //Si el nombre de usuario existe;
        boolean insertOk = (idGenerado != -1);
        if (!insertOk) {
            throw new DAOException("No se pudo registrar el usuario");
        }

        // Guardamos en el objeto el id que le ha asignado la BBDD.
        usuario.setId((int) idGenerado);
    }

    @Override
    public Usuario buscarPorNombre(String nombreUsuario) throws DAOException {
        // Ahora en vez de escribir leemos la bbdd
        SQLiteDatabase db = helper.getReadableDatabase();
        //Creamos objetos para buscar
        Cursor cursor = null;
        Usuario usuario = null;

        try {
            //Esto es lo mismo que poner SELECT * FROM usuarios WHERE nombre_usuario = 'valor'
            cursor = db.query(LetrixSQLiteHelper.TABLA_USUARIO, null, LetrixSQLiteHelper.COLUMNA_USUARIO + " = ?",
                    new String[]{ nombreUsuario }, null, null, null);

            //Poniendo db.query ya busca en toda la tabla y no es necesario un buvcle
            //Movemos el resultado a la primera fila para poder sacar el id nombre y contraseña
            boolean hayResultado = cursor.moveToFirst();
            if (hayResultado) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_USUARIO));
                String contrasena = cursor.getString(cursor.getColumnIndexOrThrow(LetrixSQLiteHelper.COLUMNA_CONTRASENA));
                usuario = new Usuario(id, nombre, contrasena);
            }
        } catch (Exception e) {
            throw new DAOException("Error al buscar el usuario", e);
        } finally {
            // Cerramos cursor
            if (cursor != null) {
                cursor.close();
            }
        }

        return usuario;
    }
}
