/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import principal.DatabaseConnection;
import principal.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Espacio;
import java.sql.ResultSet;
/**
 *
 * @author Trabajo
 */
public class PersonasDAO {
    public void insertarPersona(Personas persona, Espacio espacio) {
    String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
    String insertSql = "INSERT INTO personas (persona_id, nombre, telefono) VALUES (?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        // Verificar disponibilidad del espacio
        checkStmt.setInt(1, espacio.getid());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            boolean disponible = rs.getBoolean("Disponible");

            if (disponible) {
                // Insertar persona
                insertStmt.setObject(1, persona.getid());
                insertStmt.setString(2, persona.getNombre());
                insertStmt.setObject(3, persona.getTelefono());
                insertStmt.executeUpdate();
                
            } else {
                System.out.println("No se puede guardar persona");
            }
    
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

