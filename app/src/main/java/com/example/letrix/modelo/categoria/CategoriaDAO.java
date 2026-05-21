package com.example.letrix.modelo.categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    // Cargamos las 4 categorías para mostrarlas por pantalla.
    public List<Categoria> obtenerTodas() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getString("id"), rs.getString("nombreCategoria")));
            }
        }
        return lista;
    }

    // Recuperaramos la categoría elegida por el usuario para luego entrar en el juego
    public Categoria obtenerPorId(String id) throws SQLException {
        String sql = "SELECT * FROM Categoria WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categoria(rs.getString("id"), rs.getString("nombreCategoria"));
            }
        }
        return null;
    }
}
