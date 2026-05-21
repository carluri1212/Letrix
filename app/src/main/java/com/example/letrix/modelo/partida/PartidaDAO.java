package com.example.letrix.modelo.partida;

import java.sql.*;
public class PartidaDAO {
    private Connection connection;

    public PartidaDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear una partida nueva al empezar el juego
    public void insertar(Partida partida) throws SQLException {
        String sql = "INSERT INTO Partida (idUsuario, idPalabra, numeroIntentos, resultado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, partida.getIdUsuario());
            ps.setString(2, partida.getIdPalabra());
            ps.setInt(3, partida.getNumeroIntentos());
            ps.setInt(4, partida.isResultado() ? 1 : 0);
            ps.executeUpdate();
        }
    }

    // Actualizar la partida cuando termina (guarda intentos y resultado final)
    public void actualizar(Partida partida) throws SQLException {
        String sql = "UPDATE Partida SET numeroIntentos = ?, resultado = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, partida.getNumeroIntentos());
            ps.setInt(2, partida.isResultado() ? 1 : 0);
            ps.setInt(3, partida.getId());
            ps.executeUpdate();
        }
    }

    // Obtener la partida por id
    public Partida obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Partida WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Partida(
                        rs.getInt("id"),
                        rs.getString("idUsuario"),
                        rs.getString("idPalabra"),
                        rs.getInt("numeroIntentos"),
                        rs.getBoolean("resultado")
                );
            }
        }
        return null;
    }
}
