package com.example.letrix.modelo.palabra;

import java.sql.*;
public class PalabraDAO {

    private Connection connection;

    public PalabraDAO(Connection connection) {
        this.connection = connection;
    }

    // Metodo donde obtenemos una palabra aleatoria según la categoría que hemos elegido
    public Palabra obtenerPalabraAleatoria(String idCategoria) throws SQLException {
        String sql = "SELECT * FROM Palabra WHERE idCategoria = ? ORDER BY RAND() LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Palabra(
                        rs.getString("id"),
                        rs.getString("texto"),
                        rs.getString("idCategoria")
                );
            }
        }
        return null;
    }

    // Metodo donde obtenemos una palabra por id (Palabra.java) (Lo usamos para recuperar una partida guardada)
    public Palabra obtenerPorId(String id) throws SQLException {
        String sql = "SELECT * FROM Palabra WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Palabra(
                        rs.getString("id"),
                        rs.getString("texto"),
                        rs.getString("idCategoria")
                );
            }
        }
        return null;
    }

}
