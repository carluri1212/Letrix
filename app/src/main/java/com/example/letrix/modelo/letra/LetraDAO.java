package com.example.letrix.modelo.letra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LetraDAO {

    private Connection connection;

    public LetraDAO(Connection connection) {
        this.connection = connection;
    }

    // Guardamos una letra que el usuario introduzca
    public void insertar(Letra letra) throws SQLException {
        String sql = "INSERT INTO Letra (idPartida, caracter, posicion, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, letra.getIdPartida());
            ps.setString(2, String.valueOf(letra.getCaracter()));
            ps.setInt(3, letra.getPosicion());
            ps.setString(4, letra.getEstado().name());
            ps.executeUpdate();
        }
    }

    // Obtenemos todas las letras de la partida (para pintar el tablero)
    public List<Letra> obtenerPorPartida(int idPartida) throws SQLException {
        List<Letra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Letra WHERE idPartida = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Letra(
                        rs.getInt("id"),
                        rs.getInt("idPartida"),
                        rs.getString("caracter").charAt(0),
                        rs.getInt("posicion"),
                        EstadoLetra.valueOf(rs.getString("estado"))
                ));
            }
        }
        return lista;
    }
}
