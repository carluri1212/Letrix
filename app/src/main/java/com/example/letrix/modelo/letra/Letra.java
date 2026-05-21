package com.example.letrix.modelo.letra;

public class Letra {

    private int id;
    private int idPartida;
    private char caracter;
    private int posicion;
    private EstadoLetra estado;

    public Letra() {}

    public Letra(int id, int idPartida, char caracter, int posicion, EstadoLetra estado) {
        this.id = id;
        this.idPartida = idPartida;
        this.caracter = caracter;
        this.posicion = posicion;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPartida() {
        return idPartida;
    }
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public char getCaracter() {
        return caracter;
    }
    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public EstadoLetra getEstado() {
        return estado;
    }
    public void setEstado(EstadoLetra estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Letra{id=" + id + ", idPartida=" + idPartida + ", caracter=" + caracter +
                ", posicion=" + posicion + ", estado=" + estado + "}";
    }
}
