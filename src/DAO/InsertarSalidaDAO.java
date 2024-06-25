/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import principal.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author delga
 */
public class InsertarSalidaDAO {
public void insertarSalida(String placa) {
    String insertSalidaSql = "INSERT INTO Salida (entrada_id, fecha_salida) VALUES (?, CURRENT_TIMESTAMP())";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement insertSalidaStmt = conn.prepareStatement(insertSalidaSql)) {
        
        // Obtener el ID de la entrada correspondiente a la placa
        int entradaId = obtenerEntradaIdPorPlaca(placa);
        if (entradaId == -1) {
            System.out.println("No se encontró una entrada con la placa proporcionada.");
            return;
        }
        
        // Insertar registro en la tabla Salida
        insertSalidaStmt.setInt(1, entradaId);
        insertSalidaStmt.executeUpdate();
        
        System.out.println("Registro de salida insertado correctamente.");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public int obtenerEntradaIdPorPlaca(String placa) {
    String selectEntradaIdSql = "SELECT entrada_id FROM Entrada WHERE placa = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement selectEntradaIdStmt = conn.prepareStatement(selectEntradaIdSql)) {
        
        selectEntradaIdStmt.setString(1, placa);
        ResultSet rs = selectEntradaIdStmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("entrada_id");
        } else {
            return -1; // No se encontró una entrada con la placa proporcionada
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
}
}
