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
import java.sql.ResultSet;
import principal.Espacio;

/**
 *
 * @author Trabajo
 */
public class EntradaDAO {
   public void insertarEntrada(Entrada entrada, Espacio espacio) {
    String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
    String insertSql = "INSERT INTO entrada (placa, espacio_id, fecha_entrada) VALUES (?, ?, CURRENT_TIMESTAMP())";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        // Verificar disponibilidad del espacio
        checkStmt.setInt(1, espacio.getid());
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next()) {
            boolean disponible = rs.getBoolean("Disponible");

            if (disponible) {
                // Insertar entrada
                insertStmt.setString(1, entrada.getplaca());
                insertStmt.setInt(2, entrada.getid());
                insertStmt.executeUpdate();

            }
        } else {
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
