package com.example.letrix.modelo.categoria;

public class Categoria {

    private String id;
    private String nombreCategoria;

    public Categoria() {

    }

    public Categoria(String id, String nombreCategoria) {
        this.id = id;
        this.nombreCategoria = nombreCategoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{id='" + id + "', nombreCategoria='" + nombreCategoria + "'}";
    }

}
