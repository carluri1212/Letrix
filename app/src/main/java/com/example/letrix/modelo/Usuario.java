package com.example.letrix.modelo;

//CLASE USUARIO: Constructores,getters,etc.

public class Usuario {

    private int id;
    private String usuario;
    private String contrasena;

    public Usuario(int id, String usuario, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    public Usuario(String usuario, String contrasena) {
        this(0, usuario, contrasena);
    }
    public Usuario() {
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contrasena + '\'' +
                '}';
    }
}
