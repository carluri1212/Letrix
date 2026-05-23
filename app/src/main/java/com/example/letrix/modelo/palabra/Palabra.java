package com.example.letrix.modelo.palabra;

public class Palabra {
    //Clase Palabra para asignar una palabra según el idCategoria.

    private String id;
    private String texto;
    private String idCategoria;

    public Palabra() {

    }

    public Palabra(String id, String texto, String idCategoria) {
        this.id = id;
        this.texto = texto;
        this.idCategoria = idCategoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Palabra{id='" + id + "', texto='" + texto + "', idCategoria='" + idCategoria + "'}";
    }

}
