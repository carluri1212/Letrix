package com.example.letrix.modelo;

public interface UsuarioDAOInterfaz {
    void registrar(Usuario usuario) throws DAOException;

    Usuario buscarPorNombre(String nombreUsuario) throws DAOException;
}
