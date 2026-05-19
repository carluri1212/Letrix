package com.example.letrix.modelo;

public interface UsuarioDAOInterfaz {

    void registrar(Usuario usuario) throws DAOException; //Lanza una excepcion en caso de que haya un error en el acceso a datos.

    Usuario buscarPorNombre(String nombreUsuario) throws DAOException; // Lanza una excepcion en el caso de que no encuentra a ese usuario

}
