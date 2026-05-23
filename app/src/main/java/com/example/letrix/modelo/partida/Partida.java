package com.example.letrix.modelo.partida;

//Creamos la clase Partida para asignar los atributos de Usuario, etc. Y comenzar una partida
public class Partida {

    private int id;
    private String idUsuario; //Hacemos conexión con Usuario
    private String idPalabra; //Hacemos conexión con Palabra
    private int numeroIntentos;
    private boolean resultado; // true = ganas y false = pierde.

    //Continuamos con los getters y demás.

    public Partida() {

    }

    public Partida(int id, String idUsuario, String idPalabra, int numeroIntentos, boolean resultado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPalabra = idPalabra;
        this.numeroIntentos = numeroIntentos;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPalabra() {
        return idPalabra;
    }
    public void setIdPalabra(String idPalabra) {
        this.idPalabra = idPalabra;
    }

    public int getNumeroIntentos() {
        return numeroIntentos;
    }
    public void setNumeroIntentos(int numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public boolean isResultado() {
        return resultado;
    }
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Partida{id=" + id + ", idUsuario='" + idUsuario + "', idPalabra='" + idPalabra +
                "', numeroIntentos=" + numeroIntentos + ", resultado=" + resultado + "}";
    }

}
