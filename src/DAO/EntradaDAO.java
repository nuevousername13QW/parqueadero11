/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Entrada;
import principal.DatabaseConnection;

/**
 *
 * @author Trabajo
 */
public class EntradaDAO {
    public void insertarEntrada(Entrada entrada) {
        String sql = "INSERT INTO entrada (placa, espacio_id, fecha_entrada, hora_entrada) VALUES (?, ?,current_time(), current_date())";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entrada.getplaca());
            pstmt.setInt(2, entrada.getid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
