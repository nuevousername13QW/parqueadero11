/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import principal.DatabaseConnection;
import principal.Carros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import principal.Espacio;
/**
 *
 * @author Trabajo
 */
public class CarroDAO {
     public void insertarCarro(Carros carro, Espacio espacio) {
        String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
        String insertSql = "INSERT INTO carros (placa, marca, modelo, color, persona_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Verificar disponibilidad del espacio
            checkStmt.setInt(1, espacio.getid());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                boolean disponible = rs.getBoolean("Disponible");

                if (disponible) {
                    // Insertar carro
                    insertStmt.setString(1, carro.getplaca());
                    insertStmt.setString(2, carro.getmarca());
                    insertStmt.setString(3, carro.getmodelo());
                    insertStmt.setString(4, carro.getcolor());
                    insertStmt.setInt(5, carro.getid());
                    insertStmt.executeUpdate(); // Ejecutar la inserción
                }
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
